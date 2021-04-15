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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.util.*;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.LoggingMode;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Target;
import com.adobe.marketing.mobile.TargetOrder;
import com.adobe.marketing.mobile.TargetParameters;
import com.adobe.marketing.mobile.TargetProduct;
import com.adobe.marketing.mobile.TargetRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This activity class is responsible to show offer details
 */
public class ProductDetailsActivity extends AppCompatActivity {

    //private Handler mainHandler;
    //private View surpriseView;
   // private View imagePresentView;
    private  String productId;
    private LinearLayout currentLayout;

    // Mock data for product_id and category_id

    private Map productCat = new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileCore.setApplication(getApplication());
        MobileCore.setLogLevel(LoggingMode.VERBOSE);

        setContentView(R.layout.activity_product_details);

        // Mock data for product_id and category_id
        /*
        productCat.put("zd0001","ski equip");
        productCat.put("zd0002","ski equip");
        productCat.put("zd0003","ski apparel");
        productCat.put("zd0004","ski apparel");
         */
        /*
        productCat.put("p1001","Category Level One");
        productCat.put("p1002","Category Level One");
        productCat.put("p1003","Category Level One");
        productCat.put("p1004","Category Level One");
        productCat.put("p1005","Category Level One");
        productCat.put("p1006","Category Level One");

         */
        /*
        productCat.put("p2001","Category Level One");
        productCat.put("p2002","Category Level One");
        productCat.put("p2003","Category Level One");
        productCat.put("p2004","Category Level One");
        productCat.put("p2005","Category Level One");
        productCat.put("p2006","Category Level One");
        */

        productCat.put("p3001","Category Level One");
        productCat.put("p3002","Category Level One");
        productCat.put("p3003","Category Level One");
        productCat.put("p3004","Category Level One");
        productCat.put("p3005","Category Level One");
        productCat.put("p3006","Category Level One");

        productId = getIntent().getStringExtra("productId");

        setUpToolBar(productId);

        //Target.resetExperience();
        TargetProduct targetProduct = new TargetProduct(productId,productCat.get(productId).toString());
        TargetParameters parameters2 = new TargetParameters.Builder()
                .product(targetProduct)
                .build();

        TargetRequest request2 = new TargetRequest("AlsoPurchasedThese", parameters2, "defaultContent2",
                new AdobeCallback<String>() {
                    @Override
                    public void call(String value) {
                        // do something with target content.
                        Log.v("TargetRequest2","Handling Callback!");
                    }
                });
        List<TargetRequest> requests = new ArrayList<>();
        requests.add(request2);
        Target.retrieveLocationContent(requests, null);

        findViewById(R.id.BookingService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Target.resetExperience();

                TargetProduct targetProduct = new TargetProduct(productId,productCat.get(productId).toString());
                List<String> purchasedIds = new ArrayList<String>();
                purchasedIds.add(productId);

                TargetOrder targetOrder = new TargetOrder("ADCKKIM", 344.30, purchasedIds);

                TargetParameters parameters3 = new TargetParameters.Builder()
                        .product(targetProduct)
                        .order(targetOrder)
                        .build();

                TargetRequest request3 = new TargetRequest("AlsoPurchasedThese", parameters3, "defaultContent2",
                        new AdobeCallback<String>() {
                            @Override
                            public void call(String value) {
                                // do something with target content.
                                Log.v("TargetRequest3","Handling Callback!");
                            }
                        });
                List<TargetRequest> requests = new ArrayList<>();
                requests.add(request3);
                Target.retrieveLocationContent(requests, null);
            }
        });
        currentLayout = this.findViewById(R.id.offer_layout);
    }

    private void setUpToolBar(String productId) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        toolbar.setTitle("Product " + productId + " Details");
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
        //MobileCore.setApplication(getApplication());
        //MobileCore.lifecycleStart(null);
        //MobileCore.trackState("Booking Screen", null);
        /*
        HashMap cData = new HashMap<String, String>();
        cData.put("cd.section", "Product Listing");
        cData.put("cd.subSection", "Product Listing");
        cData.put("cd.conversionType", "Landing");
        MobileCore.trackState("Product Details", cData);
        */

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

