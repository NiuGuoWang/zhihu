package com.example.geekmvp.mygeek.ui.userinfo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekmvp.mygeek.MainActivity;
import com.example.geekmvp.mygeek.R;
import com.example.geekmvp.mygeek.model.bean.myserver.UploadHeaderBean;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.iv_userInfo)
    ImageView ivUserInfo;
    @BindView(R.id.tv_userInfo)
    TextView tvUserInfo;
    private Context context;
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private Uri imageUri;
    private String imgurl;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        context = this;
        setOut();
    }

    private void setOut() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String username = sp.getString("username", "未登录");
        String userImg = sp.getString("userImg", "userImg");
        uid = sp.getString("uid", "0");
        tvUserInfo.setText(username);

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(userImg).apply(requestOptions).into(ivUserInfo);
    }

    @OnClick(R.id.iv_userInfo)
    public void onViewClicked() {
        new AlertDialog.Builder(context)
                .setTitle("更换头像")
                .setNegativeButton("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        take_photo();
                    }
                })
                .setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_photo();
                    }
                })
                .create()
                .show();
    }



    /**
     *拍照获取图片
     **/
    public void take_photo() {
        String status= Environment.getExternalStorageState();
        if(status.equals(Environment.MEDIA_MOUNTED)) {
            //创建File对象，用于存储拍照后的图片
            File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(this, "com.llk.study.activity.PhotoActivity", outputImage);
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            //启动相机程序
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        }else
        {
            Toast.makeText(context, "没有储存卡",Toast.LENGTH_LONG).show();
        }
    }
//=================================================================
    /**
     * 从相册中获取图片
     * */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }

    /**
     * 打开相册的方法
     * */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }
    //=================================================================
//回传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO :
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        RequestOptions requestOptions = RequestOptions.circleCropTransform();
                        Glide.with(context).load(bitmap).apply(requestOptions).into(ivUserInfo);
                        saveBitmapFile(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO :
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImgeOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     *4.4以下系统处理图片的方法
     * */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }
    /**
     * 4.4及以上系统处理图片的方法
     * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri,null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            displayImage(imagePath);
        }
    }

    /**
     * 根据图片路径显示图片的方法
     * */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(context).load(bitmap).apply(requestOptions).into(ivUserInfo);
            File file = new File((imagePath));
            if(file.exists()){
                uploadPic(file);
            }
        }else {
            Toast.makeText(context,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过uri和selection来获取真实的图片路径
     * */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else {
                    Toast.makeText(context,"failed to get image",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    /**
     * @param file 将图片给上传服务器
     */
    private void uploadPic(File file) {

//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bitmap.getByteCount());
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//        byte[] bytes = outputStream.toByteArray();

        String url = "http://yun918.cn/study/public/index.php/uploadheader";
        String type = "application/octet-stream";
        OkHttpClient ok = new OkHttpClient.Builder()
                .build();
        if (file.exists()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(type), file);
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .addFormDataPart("uid", uid)
                    .addFormDataPart("key", "武哲军")
                    .addFormDataPart("file", file.getName(), requestBody)
                    .setType(MultipartBody.FORM)
                    .build();
            Request request = new Request.Builder()
                    .post(multipartBody)
                    .url(url)
                    .build();
            Call call = ok.newCall(request);
            call.enqueue(new Callback() {


                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("TAG", "img_upload==========" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Gson gson = new Gson();
                    final UploadHeaderBean bean = gson.fromJson(string, UploadHeaderBean.class);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, bean.getRes(), Toast.LENGTH_SHORT).show();
                            if (bean.getCode() == 200) {
                                SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString("userImg", bean.getData().getUrl());
                                imgurl = bean.getData().getUrl();
                                edit.commit();

                            }
                        }
                    });
                }
            });
        }
    }
    public void saveBitmapFile(Bitmap bitmap) {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/img.jpg");//将要保存图片的路径

        if (!file.exists()) {
            file.mkdir();
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            uploadPic(file);

            bos.flush();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
