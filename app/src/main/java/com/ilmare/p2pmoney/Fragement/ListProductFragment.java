package com.ilmare.p2pmoney.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ilmare.p2pmoney.Beans.Product;
import com.ilmare.p2pmoney.Beans.ProductList;
import com.ilmare.p2pmoney.Common.AppNetConfig;
import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.Utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 6:19 PM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public class ListProductFragment extends Fragment {


    @InjectView(R.id.product_list)
    ListView productList;
    private ProductList productList1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        AsyncHttpClient client=new AsyncHttpClient();
        client.get(getActivity(), AppNetConfig.PRODUCT,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                productList1 = new ProductList();
                JSONObject jsonObject= JSONObject.parseObject(content);
                String data=jsonObject.getString("data");
                List<Product> products = JSON.parseArray(data, Product.class);
                productList1.setProductList(products);

                productList.setAdapter(new MyProductListAdapter());
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });



    }

    private class MyProductListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return productList1.getProductList().size();
        }

        @Override
        public Object getItem(int position) {
            return productList1.getProductList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemProductListHolder holder=null;
            if(convertView==null){
                convertView=UIUtils.getXmlView(R.layout.item_product_list);
                holder=new ItemProductListHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder= (ItemProductListHolder) convertView.getTag();
            }

            Product product=productList1.getProductList().get(position);
            holder.getpMinzouzi().setText(product.getMinTouMoney());
            holder.getpName().setText(product.getName());
            holder.getpMoney().setText(product.getMoney());
            holder.getpYearlv().setText(product.getYearLv());
            holder.getpSuodingdays().setText(product.getSuodingDays());
            holder.getpProgresss().setProgress(Integer.parseInt(product.getProgress()));

            return convertView;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
