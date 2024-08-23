package spotters.tech.mynotepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    MainActivity.RealmResults<Note> noteslist;

    public MyAdapter(Context context, MainActivity.RealmResults<Note> noteslist) {
        this.context = context;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = noteslist.get(position);
        holder.titleOutput.setText(note.getTitle());
        holder.descriptionOutput.setText(note.getDescription());

        String formatedTime = DateFormat.getDateTimeInstance().format(note.createdTime);
        holder.timeOutput.setText(formatedTime);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Realm realm;
                        if (item.getTitle().equals("DELETE"))
                            //delete the note
                        {
                        }realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        note.deleteFromRealm();
                        realm.commitTransaction();
                        Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                menu.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }

    private class RealmResults<T> {
        public T get(int position) {
            return null;
        }

        public int size() {
            return 0;
        }
    }
}
