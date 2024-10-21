package com.example.personagen.Main;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personagen.Data.Model.User;
import com.example.personagen.R;
import com.example.personagen.UserInfo.UserInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.item> {

    Context c;
    List<User> users;
    changeListener listener;

    public UserAdapter(Context c, List<User> users, changeListener listener) {
        this.c = c;
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new item(LayoutInflater.from(c).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull item holder, int position) {
        holder.bindUsers(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class item extends RecyclerView.ViewHolder{
        TextView userNameTv, userPhoneTv, userNatTv;
        ImageView userImg, userNatImg, moreBtn;
        public item(@NonNull View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.userImg);
            userNameTv = itemView.findViewById(R.id.userNameTv);
            userPhoneTv = itemView.findViewById(R.id.userPhoneTv);
            userNatTv = itemView.findViewById(R.id.userNatTv);
            userNatImg = itemView.findViewById(R.id.userNatImg);
            moreBtn = itemView.findViewById(R.id.moreBtn);
        }
        
        public void bindUsers(User user){
            Picasso.get().load(user.getPicture().getLarge()).into(userImg);
            String name = user.getName().getFirst() + " " + user.getName().getLast();
            userNameTv.setText(name);
            userPhoneTv.setText(user.getCell());
            userNatTv.setText(user.getNat());
            userNatImg.setImageResource(getNatFlag(user.getNat()));

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(c, UserInfoActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("deleteBtnVisibility", true);
                c.startActivity(intent);
            });

            moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = null;
                    popupMenu = new PopupMenu(c, view, Gravity.END, 0, R.style.popUpMenuStyle);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    inflater.inflate(R.menu.item_more_option, popupMenu.getMenu());
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.deleteBtn){
                                listener.onDelete(user);
                                return true;
                            } else
                                return false;
                        }
                    });
                }
            });
        }
        
    }

    public interface changeListener{
        public void onDelete(User user);
    }
    
    public int getNatFlag(String nat){
        if (nat.equals("US"))
            return R.drawable.ic_flag_us;
        else if (nat.equals("GB"))
            return R.drawable.ic_flag_uk;
        else if (nat.equals("AU"))
            return R.drawable.ic_flag_au;
        else if (nat.equals("CA"))
            return R.drawable.ic_flag_ca;
        else if (nat.equals("CH"))
            return R.drawable.ic_flag_ch;
        else if (nat.equals("DE"))
            return R.drawable.ic_flag_de;
        else if (nat.equals("ES"))
            return R.drawable.ic_flag_es;
        else if (nat.equals("TR"))
            return R.drawable.ic_flag_tr;
        else if (nat.equals("UA"))
            return R.drawable.ic_flag_ua;
        else if (nat.equals("FR"))
            return R.drawable.ic_flag_fr;
        else if (nat.equals("IR"))
            return R.drawable.ic_flag_ir;
        else
            return R.drawable.ic_flag_nl;
    }
    
}
