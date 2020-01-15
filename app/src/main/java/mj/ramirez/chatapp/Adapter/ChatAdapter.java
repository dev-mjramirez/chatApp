package mj.ramirez.chatapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

import mj.ramirez.chatapp.Model.ChatMessage;
import mj.ramirez.chatapp.R;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ChatMessage> items = null;

    public ChatAdapter(){
        if(items == null){
            items = new ArrayList<>();
        }
    }

    public void setList(ArrayList<ChatMessage> list){
        items = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlSender, rlYou;
        private TextView tvMessage, tvSender;
        private TextView tvMessage2;

        ViewHolder(View view) {
            super(view);

            rlSender = view.findViewById(R.id.rlSender);
            tvMessage = view.findViewById(R.id.tvMessage);
            tvSender = view.findViewById(R.id.tvSender);

            rlYou = view.findViewById(R.id.rlYou);
            tvMessage2 = view.findViewById(R.id.tvMessage2);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_chat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;

            if (Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail())
                    .equalsIgnoreCase(items.get(position).getMessageUser())) {
                holder.rlSender.setVisibility(View.GONE);
                holder.tvMessage2.setText(items.get(position).getMessageText());
            } else {
                holder.rlYou.setVisibility(View.GONE);
                holder.tvMessage.setText(items.get(position).getMessageText());
                holder.tvSender.setText(items.get(position).getMessageUser());
            }

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
