package com.ilmare.p2pmoney.Fragement;

import android.view.View;
import android.widget.TextView;

import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.View.RoundProgress;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemProductListHolder {
    @InjectView(R.id.p_name)
    TextView pName;
    @InjectView(R.id.p_money)
    TextView pMoney;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @InjectView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @InjectView(R.id.p_progresss)
    RoundProgress pProgresss;

    public ItemProductListHolder(View view) {
        ButterKnife.inject(this, view);
    }

    public RoundProgress getpProgresss() {
        return pProgresss;
    }

    public TextView getpSuodingdays() {
        return pSuodingdays;
    }

    public TextView getpMoney() {
        return pMoney;
    }

    public TextView getpYearlv() {
        return pYearlv;
    }

    public TextView getpMinzouzi() {
        return pMinzouzi;
    }

    public TextView getpName() {
        return pName;
    }
}
