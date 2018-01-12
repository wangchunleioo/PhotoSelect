package com.photoselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.guoxiaoxing.phoenix.core.PhoenixOption;
import com.guoxiaoxing.phoenix.core.common.PhoenixConstant;
import com.guoxiaoxing.phoenix.core.listener.OnPickerListener;
import com.guoxiaoxing.phoenix.core.model.MediaEntity;
import com.guoxiaoxing.phoenix.core.model.MimeType;
import com.guoxiaoxing.phoenix.picker.Phoenix;
import com.photoselect.adapter.MyAdapter;
import com.photoselect.utils.SpacesItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //功能 - 选择图片/视频/音频
    public static final int TYPE_PICK_MEDIA = 0x000001;
    //功能 - 拍照
    public static final int TYPE_TAKE_PICTURE = 0x000002;
    //功能 - 预览
    public static final int TYPE_BROWSER_PICTURE = 0x000003;
    //网格布局管理器
    private GridLayoutManager mGridLayoutManager;
    //条目间隔距离设置
    private SpacesItemDecoration mSpacesItemDecoration;
    //适配器
    private MyAdapter mAdapter;
    @Bind(R.id.btn_select)
    Button mBtnSelect;
    @Bind(R.id.activity_main)
    RelativeLayout mActivityMain;
    @Bind(R.id.recycler)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        setListener();
    }

    private void initData() {
        //初始化一个3列的纵向网格布局
        mGridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        //初始化条目间距
        mSpacesItemDecoration = new SpacesItemDecoration(16);
        //初始化适配器
        mAdapter = new MyAdapter();
    }

    private void initView() {
        //设置网格布局管理器
        mRecycler.setLayoutManager(mGridLayoutManager);
        //设置条目距离
        mRecycler.addItemDecoration(mSpacesItemDecoration);
        //设置适配器
        mRecycler.setAdapter(mAdapter);
    }

    private void setListener() {
        mBtnSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select:
                function();
                break;
        }
    }

    public void function() {
        Phoenix.with()
                .theme(PhoenixOption.THEME_DEFAULT)// 主题
                .fileType(MimeType.ofAll())//显示的文件类型图片、视频、图片和视频
                .maxPickNumber(10)// 最大选择数量
                .minPickNumber(0)// 最小选择数量
                .spanCount(4)// 每行显示个数
                .pickMode(PhoenixConstant.MULTIPLE)// 多选/单选
                .enablePreview(true)// 是否开启预览
                .enableCamera(true)// 是否开启拍照
                .enableAnimation(true)// 选择界面图片点击效果
                .enableCompress(true)// 是否开启压缩
                .thumbnailHeight(160)// 选择界面图片高度
                .thumbnailWidth(160)// 选择界面图片宽度
                .enableClickSound(true)//ƒ 是否开启点击声音
//                .pickedMediaList(pickList)// 已选图片数据
                .videoSecond(0)//显示多少秒以内的视频
                .onPickerListener(new OnPickerListener() {
                    @Override
                    public void onPickSuccess(List<MediaEntity> pickList) {
                        mAdapter.addData(pickList);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onPickFailed(String errorMessage) {

                    }
                }).start(MainActivity.this, PhoenixOption.TYPE_PICK_MEDIA);
    }
}
