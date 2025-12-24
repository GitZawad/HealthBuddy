package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor Name : Zhang Lee", "Hospital Address : Beijing", "Exp : 5 years", "Contact No : 16696172098", "400"},
            {"Doctor Name : Li Mei", "Hospital Address : Guangzhou", "Exp : 8 years", "Contact No : 16696174521", "350"},
            {"Doctor Name : Chen Hao", "Hospital Address : Shenzhen", "Exp : 15 years", "Contact No : 16696175236", "600"},
            {"Doctor Name : Liu Yan", "Hospital Address : Chengdu", "Exp : 6 years", "Contact No : 16696176890", "300"}
    };

    private String[][] doctor_details2 = {
            {"Doctor Name : Liu Yan", "Hospital Address : Chengdu", "Exp : 6 years", "Contact No : 16696176890", "300"},
            {"Doctor Name : Zhao Min", "Hospital Address : Xi'an", "Exp : 9 years", "Contact No : 16696177234", "420"},
            {"Doctor Name : Sun Tao", "Hospital Address : Nanjing", "Exp : 11 years", "Contact No : 16696178567", "480"},
            {"Doctor Name : Wu Fang", "Hospital Address : Wuhan", "Exp : 7 years", "Contact No : 16696179012", "380"}
    };

    private String[][] doctor_details3 = {
            {"Doctor Name : Zhou Lin", "Hospital Address : Tianjin", "Exp : 14 years", "Contact No : 16696180543", "520"},
            {"Doctor Name : Xu Ming", "Hospital Address : Chongqing", "Exp : 10 years", "Contact No : 16696181234", "450"},
            {"Doctor Name : Huang Wei", "Hospital Address : Suzhou", "Exp : 8 years", "Contact No : 16696182345", "400"},
            {"Doctor Name : Lin Na", "Hospital Address : Xiamen", "Exp : 13 years", "Contact No : 16696183456", "500"}
    };

    private String[][] doctor_details4 = {
            {"Doctor Name : Ma Hong", "Hospital Address : Qingdao", "Exp : 6 years", "Contact No : 16696184567", "350"},
            {"Doctor Name : Zhu Yan", "Hospital Address : Dalian", "Exp : 16 years", "Contact No : 16696185678", "580"},
            {"Doctor Name : Qin Gang", "Hospital Address : Shenyang", "Exp : 9 years", "Contact No : 16696186789", "420"},
            {"Doctor Name : Tang Li", "Hospital Address : Harbin", "Exp : 11 years", "Contact No : 16696187890", "470"}
    };

    private String[][] doctor_details5 = {
            {"Doctor Name : Song Jia", "Hospital Address : Changsha", "Exp : 7 years", "Contact No : 16696188901", "390"},
            {"Doctor Name : Yuan Feng", "Hospital Address : Zhengzhou", "Exp : 18 years", "Contact No : 16696189012", "620"},
            {"Doctor Name : Luo Wei", "Hospital Address : Kunming", "Exp : 5 years", "Contact No : 16696190123", "320"},
            {"Doctor Name : Peng Chao", "Hospital Address : Nanning", "Exp : 12 years", "Contact No : 16696191234", "480"}
    };
    TextView tv;
    Button btn;
    String[][] doctor_details;
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewBMDOrderDetails);
        btn = findViewById(R.id.buttonOrderDetailsBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician") == 0){
            doctor_details = doctor_details1;
        }

        else if(title.compareTo("Dietician") == 0){
            doctor_details = doctor_details2;
        }

        else if(title.compareTo("Dentist") == 0){
            doctor_details = doctor_details3;
        }

        else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctor_details4;
        }

        else{
            doctor_details = doctor_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Consultant Fees : "+ doctor_details[i][4] + " RMB");
            list.add(item);

        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
                );

        ListView lst = findViewById(R.id.listViewOrderDetailsList);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });

    }
}