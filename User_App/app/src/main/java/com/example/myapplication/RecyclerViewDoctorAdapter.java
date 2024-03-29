package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.FirebaseCollection.ClinicCollection;
import com.example.myapplication.FirebaseCollection.CollectionName;

import java.util.ArrayList;

public class RecyclerViewDoctorAdapter extends RecyclerView.Adapter<RecyclerViewDoctorAdapter.ViewHolderDoctor> implements Filterable {


    Global_Variables global_variables;


    ArrayList<ClinicCollection> items = new ArrayList<>();
    ArrayList<ClinicCollection> fullItems = new ArrayList<>();

    final Filter filterUser = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchText = charSequence.toString().toLowerCase();
            ArrayList<ClinicCollection> temp = new ArrayList<>();

            if (searchText.isEmpty()) {
                temp.addAll(fullItems);
            } else {
                for (ClinicCollection item : fullItems) {
                    if (item.getDoctor().get(CollectionName.Fields.name.name()).toString().toLowerCase().contains(searchText)
                            || item.getPrice().contains(searchText)
                            || item.getClinic_Address().get(CollectionName.Fields.street.name()).toString().toLowerCase().contains(searchText))

                        temp.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = temp;
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            items.clear();
            items.addAll((java.util.Collection<? extends ClinicCollection>) filterResults.values);
            notifyDataSetChanged();
        }
    };
    Context mContext;
    com.example.myapplication.OnClickListener mListener;

    public RecyclerViewDoctorAdapter(ArrayList<ClinicCollection> items, Context mContext, com.example.myapplication.OnClickListener listener) {
        this.items = items;
        this.mContext = mContext;
        this.fullItems = new ArrayList<>(this.items);
        this.mListener = listener;

    }

    @NonNull
    @Override
    public ViewHolderDoctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        global_variables = new Global_Variables();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsv_item_doctors, parent, false);
        ViewHolderDoctor viewHolderDoctor = new ViewHolderDoctor(view);
        return viewHolderDoctor;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoctor holder, int position) {

        ClinicCollection clinicCollection = items.get(position);

        holder.tv_clinicName.setText(clinicCollection.getClinicName().toString());
        holder.tvPrice.setText(String.valueOf(clinicCollection.getPrice()).toString());
        holder.tSpecialize.setText(clinicCollection.getTypeOfClinic().toString());

        holder.tvName.setText(clinicCollection.getDoctor().get(CollectionName.Fields.name.name()).toString());
        holder.tvEmail.setText(clinicCollection.getDoctor().get(CollectionName.Fields.email.name()).toString());
        holder.tvPhone.setText(clinicCollection.getDoctor().get(CollectionName.Fields.telephones.name()).toString());
        holder.tvEducationDegree.setText(clinicCollection.getDoctor().get(CollectionName.Fields.educationDegree.name()).toString());
        holder.kuraimi_Account.setText(clinicCollection.getDoctor().get(CollectionName.Fields.kuraimiAccount.name()).toString());

        holder.tvAddress.setText(clinicCollection.getClinic_Address().get(CollectionName.Fields.street.name()).toString());
        holder.longitude.setText(clinicCollection.getClinic_Address().get(CollectionName.Fields.longitude.name()).toString());
        holder.latitude.setText(clinicCollection.getClinic_Address().get(CollectionName.Fields.latitude.name()).toString());


        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return filterUser;
    }

    class ViewHolderDoctor extends RecyclerView.ViewHolder {

        TextView tvName, tSpecialize, tvPrice, tv_clinicName, tvEmail, tvPhone, tvAddress, tvEducationDegree, kuraimi_Account, longitude, latitude;
        Button btnDetails;

        public ViewHolderDoctor(@NonNull View itemView) {
            super(itemView);


            tvName = itemView.findViewById(R.id.tv_doctor_name);
            tSpecialize = itemView.findViewById(R.id.tv_doctor_specialize);
            tvPrice = itemView.findViewById(R.id.tv_booking_price);
            tv_clinicName = itemView.findViewById(R.id.tv_clinic_name);
            tvEmail = itemView.findViewById(R.id.tv_doctor_email);
            tvPhone = itemView.findViewById(R.id.tv_doctor_phone);
            tvAddress = itemView.findViewById(R.id.tv_doctor_address);
            tvEducationDegree = itemView.findViewById(R.id.tv_Doctor_EducationDegree);
            kuraimi_Account = itemView.findViewById(R.id.tv_doctor_KuraimiAccount);
            longitude = itemView.findViewById(R.id.tv_position_longitude);
            latitude = itemView.findViewById(R.id.tv_position_latitude);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}
