package com.example.contact_amarvirsingh_783273;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> implements Filterable {

  private Context context;
  ArrayList<ContactModelClass> contactList;
  HelperClass helper ;
  ArrayList<ContactModelClass> filterList;

    public ContactAdapter(Context context, ArrayList<ContactModelClass> contactList, HelperClass helper) {
        this.context = context;
        this.contactList = contactList;
        this.helper = helper;
        this.filterList = contactList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.email.setText(filterList.get(position).getEmailId());
        holder.first.setText(filterList.get(position).getFirstName());
        holder.last.setText(filterList.get(position).getLastName());
        holder.address.setText(filterList.get(position).getAddress());
        holder.number.setText(filterList.get(position).getPhoneNumber());

       final ContactModelClass model = filterList.get(position);

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Do you Want to Delete this contact");
                builder.setMessage("Are you sure ????");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        long result = helper.deleteContact(model.getId());
                        if (result != -1){
                            Toast.makeText(context, "Contact Delete successFully ", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            filterList.remove(position);

                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();


            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",model.getId());
                context.startActivity(intent);
            }
        });


holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL,new String[]{model.getEmailId()});
        email.setType("message/rfc822");
        context.startActivity(Intent.createChooser(email,"How To send mail"));
       // Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();

        return true;
    }
});

    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public Filter getFilter() {
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charcater = constraint.toString();
                if (charcater.isEmpty()){
                    filterList = contactList ;
                }else {
                    ArrayList<ContactModelClass> newfilterList = new ArrayList<>();
                    for (ContactModelClass row: contactList){
                        if (row.getFirstName().toLowerCase().contains(charcater.toLowerCase()) || row.getLastName().toLowerCase().contains(charcater.toLowerCase())){
                            newfilterList.add(row);
                        }
                    }

                    filterList = newfilterList ;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<ContactModelClass>) results.values ;
                notifyDataSetChanged();
            }
        };






    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView first, last, address, number, email;
        Button deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            first = itemView.findViewById(R.id.row_firstName);
            last = itemView.findViewById(R.id.row_lastName);
            address = itemView.findViewById(R.id.row_address);
            number = itemView.findViewById(R.id.row_number);
            email = itemView.findViewById(R.id.row_email);
            deleteBtn = itemView.findViewById(R.id.row_btnDelete);



        }
    }

}
