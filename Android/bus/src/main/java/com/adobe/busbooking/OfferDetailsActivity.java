/*************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 *  Copyright 2018 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property
 * laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/
package com.adobe.busbooking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adobe.marketing.mobile.MobileCore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
/**
 * This activity class is responsible to show offer details
 */
public class OfferDetailsActivity extends AppCompatActivity {

    private Handler mainHandler;
    private View surpriseView;
   // private View imagePresentView;
    private LinearLayout currentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        setUpToolBar();
        //mainHandler = new Handler(getApplicationContext().getMainLooper());

        /*surpriseView = getLayoutInflater().inflate(R.layout.suprise_layout,
                (ViewGroup) findViewById(android.R.id.content), false);*/

        //imagePresentView = this.findViewById(R.id.image_present);
        currentLayout = this.findViewById(R.id.offer_layout);

        Map productView = new HashMap<String,Integer>();
        /*
        productView.put("p1001",R.id.p1001);
        productView.put("p1002",R.id.p1002);
        productView.put("p1003",R.id.p1003);
        productView.put("p1004",R.id.p1004);
        productView.put("p1005",R.id.p1005);
        productView.put("p1006",R.id.p1006);
        */

        /*
        productView.put("p2001",R.id.p1001);
        productView.put("p2002",R.id.p1002);
        productView.put("p2003",R.id.p1003);
        productView.put("p2004",R.id.p1004);
        productView.put("p2005",R.id.p1005);
        productView.put("p2006",R.id.p1006);
        */

        productView.put("p3001",R.id.p1001);
        productView.put("p3002",R.id.p1002);
        productView.put("p3003",R.id.p1003);
        productView.put("p3004",R.id.p1004);
        productView.put("p3005",R.id.p1005);
        productView.put("p3006",R.id.p1006);

        /*
        productView.put("zd0001",R.id.p1001);
        productView.put("zd0002",R.id.p1002);
        productView.put("zd0003",R.id.p1003);
        productView.put("zd0004",R.id.p1004);
        */


        Iterator <Map.Entry<String,Integer>> itr = productView.entrySet().iterator();
        while(itr.hasNext()) {
            final Map.Entry<String,Integer> entry = itr.next();

            findViewById(entry.getValue()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OfferDetailsActivity.this, ProductDetailsActivity.class);
                    intent.putExtra("productId", entry.getKey());
                    startActivity(intent);

                }
            });
        }
        /*
        findViewById(R.id.p1001).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfferDetailsActivity.this,ProductDetailsActivity.class);
                intent.putExtra("productId","p1001");
                startActivity(intent);
                //startActivity(new Intent(OfferDetailsActivity.this, ProductDetailsActivity.class));

            }
        });
        */
    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        toolbar.setTitle("Buy 1 Get 1");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setSurpriseClickListener();
        MobileCore.setApplication(getApplication());
        MobileCore.lifecycleStart(null);
        //MobileCore.trackState("Booking Screen", null);
        HashMap cData = new HashMap<String, String>();
        cData.put("cd.section", "Bus Booking");
        cData.put("cd.subSection", "Offer");
        cData.put("cd.conversionType", "Landing");
        MobileCore.trackState("Offer Details", cData);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
/*
    private void setSurpriseClickListener() {
        imagePresentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Surprise!", Toast.LENGTH_SHORT).show();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        currentLayout.addView(surpriseView);
                        Map<String, String> profileParams = new HashMap<>();
                        profileParams.put("surprisekey1", "surprisevalue1");
                        currentLayout.invalidate();
                    }
                });
                // One-shot.  Remove clicker afterwards.
                imagePresentView.setOnClickListener(null);
            }
        });
    }
*/
}

