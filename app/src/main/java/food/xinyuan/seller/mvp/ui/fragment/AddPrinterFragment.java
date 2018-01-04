package food.xinyuan.seller.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import food.xinyuan.seller.R;
import food.xinyuan.seller.app.base.AbstractMyBaseFragment;
import food.xinyuan.seller.app.data.event.EventConstant;
import food.xinyuan.seller.app.utils.CommonUtils;
import food.xinyuan.seller.app.utils.DialogUtils;
import food.xinyuan.seller.app.utils.L;
import food.xinyuan.seller.di.component.DaggerAddPrinterComponent;
import food.xinyuan.seller.di.module.AddPrinterModule;
import food.xinyuan.seller.mvp.contract.AddPrinterContract;
import food.xinyuan.seller.mvp.presenter.AddPrinterPresenter;


public class AddPrinterFragment extends AbstractMyBaseFragment<AddPrinterPresenter>
        implements AddPrinterContract.View {


    @BindView(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @BindView(R.id.tv_header_center)
    TextView tvHeaderCenter;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_device_id)
    EditText etDeviceId;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.tv_page_type)
    TextView tvPageType;
    @BindView(R.id.tv_copies)
    TextView tvCopies;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.et_device_name)
    EditText etName;
    @BindView(R.id.tv_save)
    TextView tvSave;

    MaterialDialog mDialog;

    public static AddPrinterFragment newInstance() {
        AddPrinterFragment fragment = new AddPrinterFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAddPrinterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addPrinterModule(new AddPrinterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_printer, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvHeaderCenter.setText("添加设备");
        CommonUtils.setBack(this, ivHeaderLeft);

        mDialog = new MaterialDialog.Builder(getActivity()).content(R.string.waiting).
                progress(true, 0).build();
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

    int mTypeSelected, mPageTypeSelected, mCopiesSelected;  //已选择的选项

    @OnClick({R.id.tv_type, R.id.tv_page_type, R.id.tv_copies, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //FIXME 选择易联云的时候会提示 msg=不支持k1,k2,k3机型
            case R.id.tv_type:
                DialogUtils.singleChoiceDialog(getActivity(), R.array.printer_types, mTypeSelected,
                        (dialog, itemView, which, text) -> {
                            tvType.setText(text);
                            mTypeSelected = which;
                            return true;
                        }).show();
                break;
            case R.id.tv_page_type:
                DialogUtils.singleChoiceDialog(getActivity(), R.array.printer_page_types, mPageTypeSelected,
                        (dialog, itemView, which, text) -> {
                            tvPageType.setText(text);
                            mPageTypeSelected = which;
                            return true;
                        }).show();
                break;
            case R.id.tv_copies:
                DialogUtils.singleChoiceDialog(getActivity(), R.array.printer_copies, mCopiesSelected,
                        (dialog, itemView, which, text) -> {
                            tvCopies.setText(text);
                            mCopiesSelected = which;
                            return true;
                        }).show();
                break;
            case R.id.tv_save:
                mPresenter.addPrinter(etName.getText().toString().trim(),
                        etDeviceId.getText().toString().trim(), etKey.getText().toString().trim(),
                        tvType.getText().toString(), tvPageType.getText().toString(),
                        etRemark.getText().toString().trim(), tvCopies.getText().toString());
                break;
        }
    }

    @Override
    public void addPrinterSuc() {
        ArmsUtils.makeText(getActivity(), "添加设备成功");
        EventBus.getDefault().post(EventConstant.UPDATE_PRINTER_LIST);
        pop();
    }
}
