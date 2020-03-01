package com.ephrem.myproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    public DatabaseHelper db;
    private Context context;
    public Cursor cursor;


public RecyclerAdapter(Cursor cursor, Context context){
    this.cursor = cursor;
    this.context=context;
    db = new DatabaseHelper(context);

}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView Textview_for_Username, Textview_for_Fullname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.imageView);
            Textview_for_Username = itemView.findViewById(R.id.textView);
            Textview_for_Fullname = itemView.findViewById(R.id.fullname_id);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

   if(!cursor.moveToPosition(position )){
       return;
   }

   final String Username,Fullname,Email,Phnumber,Gender;

       //     db = new DatabaseHelper(this);



        Fullname= cursor.getString(cursor.getColumnIndex(db.COLON_1));
        Username= cursor.getString(cursor.getColumnIndex(db.COLON_2));
        Email= cursor.getString(cursor.getColumnIndex(db.COLON_3));
        Phnumber= cursor.getString(cursor.getColumnIndex(db.COLON_5));
        Gender= cursor.getString(cursor.getColumnIndex(db.COLON_6));

        holder.Textview_for_Username.setText(Username);
        holder.Textview_for_Fullname.setText(Fullname);

        final Intent intent = new Intent(context,User_Detail.class);
        intent.putExtra("fullname",Fullname);
        intent.putExtra("username",Username);
        intent.putExtra("email",Email);
        intent.putExtra("phnumber",Phnumber);
        intent.putExtra("gender",Gender);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               db.delete(Email);
                return false;
            }
        });

}


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor cursor_new){
    if(cursor != null){
        cursor.close();
    }
        cursor = cursor_new;
    if(cursor_new != null){
        notifyDataSetChanged();
    }

    }




}
