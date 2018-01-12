# PhotoSelect
在项目的build.gradle中添加：<br>
allprojects {<br>
    repositories {<br>
        jcenter()<br>
        //就是他<br>
        maven { url 'https://jitpack.io' }<br>
    }<br>
}<br>
在app下的build.gradle中添加：<br>
    //图片/视频选择、拍照、图片/视频预览<br>
    compile 'com.github.guoxiaoxing.phoenix:phoenix-ui:0.0.11'<br>
    //选填 - 图片压缩，开启功能：Phoenix.with().enableCompress(true)，获取结果：MediaEntity.getCompressPath()<br>
    compile 'com.github.guoxiaoxing.phoenix:phoenix-compress-picture:0.0.11'<br>
    //选填 - 视频压缩，开启功能：Phoenix.with().enableCompress(true)，获取结果：MediaEntity.getCompressPath()<br>
    compile 'com.github.guoxiaoxing.phoenix:phoenix-compress-video:0.0.11'<br>
要把minSdkVersion改成16，不然会报错<br>
<br>
然后就是一些方法：<br>
Phoenix.with()<br>
                .theme(PhoenixOption.THEME_DEFAULT)// 主题<br>
                .fileType(MimeType.ofAll())//显示的文件类型图片、视频、图片和视频<br>
                .maxPickNumber(10)// 最大选择数量<br>
                .minPickNumber(0)// 最小选择数量<br>
                .spanCount(4)// 每行显示个数<br>
                .enablePreview(true)// 是否开启预览<br>
                .enableCamera(true)// 是否开启拍照<br>
                .enableAnimation(true)// 选择界面图片点击效果<br>
                .enableCompress(true)// 是否开启压缩<br>
                .thumbnailHeight(160)// 选择界面图片高度<br>
                .thumbnailWidth(160)// 选择界面图片宽度<br>
                .enableClickSound(true)//ƒ 是否开启点击声音<br>
//                .pickedMediaList(pickList)// 已选图片数据<br>
                .videoSecond(0)//显示多少秒以内的视频<br>
                .onPickerListener(new OnPickerListener() {<br>
                    @Override<br>
                    public void onPickSuccess(List<MediaEntity> pickList) {<br>
                        //这里就是选完照片后的回调 上面的集合就是选中照片的集合 照片的本地地址是：pickList.get(position).getCompressPath()<br>
                        //项目中我用RecyclerView来显示选中的照片<br>
                        //mAdapter.addData(pickList);<br>
                        //mAdapter.notifyDataSetChanged();<br>
                    }<br>

                    @Override
                    public void onPickFailed(String errorMessage) {

                    }
                }).start(MainActivity.this, PhoenixOption.TYPE_PICK_MEDIA);
                
   可以自由选择开启或关闭 功能强大精致 <br>
   我这里预览后对图片做处理（拉伸、裁剪、马赛克等）后不会保存修改的照片 还未找到原因... 主要是为了试试github<br>
