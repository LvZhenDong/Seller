package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.request.AddGoods;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.di.component.DaggerPrinterSettingComponent;
import food.xinyuan.seller.di.module.PrinterSettingModule;
import food.xinyuan.seller.mvp.contract.PrinterSettingContract;
import food.xinyuan.seller.mvp.presenter.PrinterSettingPresenter;
import food.xinyuan.seller.mvp.ui.adapter.PrinterAdapter;


public class PrinterSettingFragment extends AbstractMyBaseFragment<PrinterSettingPresenter> implements PrinterSettingContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_printer)
    RecyclerView rvPrinter;

    PrinterAdapter mAdapter;

    MaterialDialog mDialog;

    public static PrinterSettingFragment newInstance() {
        PrinterSettingFragment fragment = new PrinterSettingFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerPrinterSettingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .printerSettingModule(new PrinterSettingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_printer_setting, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText(R.string.printer_setting);
        CommonUtils.setBack(this, ivHeaderLeft);
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setTextSize(26);
        tvHeaderRight.setText("+");

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();


        mAdapter = new PrinterAdapter(R.layout.item_printer);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.tv_del_printer) {
                DialogUtils.commonChooseDialog(getActivity(), "确定删除改打印设备吗？",
                        (dialog, which) -> mPresenter.delPrinter(mAdapter.getData().get(position).getPrinterId() + "", position)).show();

            }
        });
        rvPrinter.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPrinter.setAdapter(mAdapter);

        mPresenter.getPrinters();
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        if (mDialog != null)
            mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog != null)
            mDialog.dismiss();
    }

    /**
     * 刷新list
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddSpec(String msg) {
        if (TextUtils.equals(msg, EventConstant.UPDATE_PRINTER_LIST))
            mPresenter.refreshPrinters();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getPrintersSuc(List<Printer> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void delPrinterSuc(int pos) {
        mAdapter.remove(pos);
    }

    @OnClick(R.id.tv_header_right)
    public void onViewClicked() {
        start(AddPrinterFragment.newInstance());
    }
}
