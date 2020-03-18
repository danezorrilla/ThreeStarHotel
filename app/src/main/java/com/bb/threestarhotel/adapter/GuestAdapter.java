package com.bb.threestarhotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.threestarhotel.R;
import com.bb.threestarhotel.model.Guest;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder>{

    private List<Guest> guestList;
    private GuestInterface guestInterface;

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guest_info, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        holder.nameTextView.setText(guestList.get(position).getGuestName());
        holder.roomNumberText.setText(guestList.get(position).getGuestRoomNumber());

        holder.itemView.setOnClickListener(view -> {
            System.out.println("Guest is clicked");
            guestInterface.deleteGuest(guestList.get(position));
        });

        Glide.with(holder.itemView.getContext())
                .load(guestList.get(position).getGuestPicture())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public interface GuestInterface{
        void deleteGuest(Guest deleteGuest);
    }

    public GuestAdapter(List<Guest> guestList, GuestInterface guestInterface) {
        this.guestList = guestList;
        this.guestInterface = guestInterface;
    }


    public class GuestViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.guest_picture)
        ImageView imageView;

        @BindView(R.id.guest_name)
        TextView nameTextView;

        @BindView(R.id.guest_room_number)
        TextView roomNumberText;

        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
