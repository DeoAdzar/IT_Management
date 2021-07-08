package com.example.it_management.ui.KnowledgeBase;

import android.content.Context;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_management.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class KBArticlesAdapterData extends RecyclerView.Adapter<KBArticlesAdapterData.HolderDataKbArticles> {
    private Context ctx;
    private List<KnowledgeBaseArticlesModel> articlesModelList;

    public KBArticlesAdapterData(Context ctx, List<KnowledgeBaseArticlesModel> articlesModelList) {
        this.ctx = ctx;
        this.articlesModelList = articlesModelList;
    }

    @NonNull
    @Override
    public HolderDataKbArticles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_kb_articles,parent,false);
        HolderDataKbArticles holder = new HolderDataKbArticles(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataKbArticles holder, int position) {
        KnowledgeBaseArticlesModel kam = articlesModelList.get(position);

        holder.tvid.setText("KB"+String.valueOf(kam.getId()));
        holder.tvidcategory.setText(String.valueOf(kam.getCategoryid()));
        holder.tvclients.setText(kam.getClients());
        holder.tvnama.setText(kam.getName());
        holder.tvcontent.setText(kam.getContent());
    }

    @Override
    public int getItemCount() {
        return articlesModelList.size();
    }

    public class HolderDataKbArticles extends RecyclerView.ViewHolder{
        private TextView tvid,tvnama,tvclients,tvidcategory,tvcontent;
        public HolderDataKbArticles(@NonNull View itemView) {
            super(itemView);
            init(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final BottomSheetDialog bt = new BottomSheetDialog(ctx,R.style.AppBottomSheetDialogTheme);
                    v = LayoutInflater.from(ctx).inflate(R.layout.bs_kb_articles,null);
                    TextView id = v.findViewById(R.id.bs_id_kb_articles);
                    id.setText(tvid.getText().toString());
                    TextView nama = v.findViewById(R.id.bs_name_kb_articles);
                    nama.setText(tvnama.getText().toString());
                    TextView content = v.findViewById(R.id.bs_content_kb_articles);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        content.setText(Html.fromHtml(tvcontent.getText().toString(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        content.setText(Html.fromHtml(tvcontent.getText().toString()));
                    }
                    bt.setContentView(v);
                    bt.show();
                }
            });
        }
        private void init(@NonNull View itemView){
            tvcontent = itemView.findViewById(R.id.tv_content_kb_articles);
            tvid = itemView.findViewById(R.id.tv_id_kb_articles);
            tvnama = itemView.findViewById(R.id.tv_nama_kb_articles);
            tvclients = itemView.findViewById(R.id.tv_kb_articles_client);
            tvidcategory = itemView.findViewById(R.id.tv_id_category_kb_articles);
        }
    }
}
