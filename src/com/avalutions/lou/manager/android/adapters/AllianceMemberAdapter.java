package com.avalutions.lou.manager.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.avalutions.lou.manager.R;
import com.avalutions.lou.manager.net.commands.responses.poll.Member;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: benny
 * Date: 9/13/12
 * Time: 2:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class AllianceMemberAdapter extends ArrayAdapter<Member> {
    private List<Member> members;
    public AllianceMemberAdapter(Context context, List<Member> members) {
        super(context, R.layout.alliance_member, members);
        this.members = members;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Member member = members.get(position);
        TextView textView = new TextView(getContext());
        textView.setText(member.name);
        return textView;
    }
}
