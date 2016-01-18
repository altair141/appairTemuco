package capaObjetos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import myapplicacion.altair141.airetemuco.R;
/**
 * Created by altair141 on 20-11-2015.
 */
public class AdaptadorListaExpandible  extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> laptopCollections;
    private List<String> laptops;

    public AdaptadorListaExpandible(Activity context, List<String> laptops,
                                    Map<String, List<String>> laptopCollections) {
        this.context = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String laptop = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.glosario_child, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.laptop);

        convertView.setBackgroundColor(Color.BLACK);
        convertView.setAlpha((float)0.8);

        item.setText(laptop);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return laptops.get(groupPosition);
    }

    public int getGroupCount() {
        return laptops.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.glosario_group,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.laptop);

        item.setTypeface(null, Typeface.BOLD);

        item.setText(laptopName);
        convertView.setBackgroundColor(Color.BLACK);
        if(groupPosition%2==0){
            convertView.setBackgroundColor(Color.parseColor("#66000000"));
            //  convertView.setAlpha((float)0.4);a

        }else {
            convertView.setBackgroundColor(Color.parseColor("#99000000"));
            //convertView.setBackgroundColor(99000000);
        }
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}