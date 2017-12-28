package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.bean.response.Printer;
import food.xinyuan.seller.app.utils.CommonUtils;
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

        mAdapter=new PrinterAdapter(R.layout.item_printer);
        rvPrinter.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPrinter.setAdapter(mAdapter);

        mPresenter.getPrinters();
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getPrintersSuc(List<Printer> data) {
        mAdapter.setNewData(data);
    }
}
