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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.adobe.marketing.mobile.AdobeCallback;
import com.adobe.marketing.mobile.Analytics;
import com.adobe.marketing.mobile.Identity;
import com.adobe.marketing.mobile.InvalidInitException;
import com.adobe.marketing.mobile.Lifecycle;
import com.adobe.marketing.mobile.LoggingMode;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.Signal;
import com.adobe.marketing.mobile.Target;
import com.adobe.marketing.mobile.TargetParameters;
import com.adobe.marketing.mobile.UserProfile;

import com.adobe.marketing.mobile.TargetPrefetch;
import com.adobe.marketing.mobile.TargetRequest;

import static com.adobe.marketing.mobile.MobileCore.getApplication;

public class OfferFragment extends Fragment {

    public static final String TAG = OfferFragment.class.getName();
    private static OfferFragment fragment;
    private TextView mPromTitle,mPromBody;
    private View view;

    public static OfferFragment getInstance() {
        if (fragment == null) {
            fragment = new OfferFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MobileCore.setApplication(getApplication());
        MobileCore.setLogLevel(LoggingMode.VERBOSE);
        final Activity activity = getActivity();
        if (activity == null) {
            Log.e(TAG, "Fragment activity null");
            return;
        }

        // define parameters for first request

        //Map<String, String> mboxParameters1 = new HashMap<>();
        //mboxParameters1.put("status", "platinum");

        //TargetParameters parameters1 = new TargetParameters.Builder().parameters(mboxParameters1).build();


        Target.resetExperience();
        TargetRequest targetRequest1 = new TargetRequest("CarTipsHome", null
                , "{\"text\":\"default\",\"url\":\"https://www.landrover.com.cn/\"}"
                , new AdobeCallback<String>() {
            @Override
            public void call(String jsonResponse) {
                //Snackbar.make(view, "Content received", Snackbar.LENGTH_LONG)
                   //     .setAction("Action", null).show();
                // so this should be JSON content...
                try {
                    JSONObject targetJSONResponse = new JSONObject(jsonResponse);
                    // replace content as needed
                    final String textForTextView = targetJSONResponse.getString("text");
                    Log.v("Target Resonpose",textForTextView);
                    final TextView textView1 = activity.findViewById(R.id.prom_title);

                    final String urlForWebViewAsText = targetJSONResponse.getString("url");
                    Log.v("Target Resonpose",urlForWebViewAsText);
                    URL url = new URL(urlForWebViewAsText); // I like to check my URLs
                    final WebView webView = activity.findViewById(R.id.prom_webcontent);
                    if (urlForWebViewAsText.length() > 0) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView1.setText(textForTextView);
                                webView.loadUrl(urlForWebViewAsText);
                                //Snackbar.make(view, "Content replaced", Snackbar.LENGTH_LONG)
                                  //      .setAction("Action", null).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    //Snackbar.make(view, "Content from Target not valid JSON", Snackbar.LENGTH_LONG)
                       //     .setAction("Action", null).show();
                    Log.v("JSON Error:",e.toString());
                } catch (MalformedURLException e) {
                   // Snackbar.make(view, "Target returned invalid URL", Snackbar.LENGTH_LONG)
                           // .setAction("Action", null).show();
                }
            }
        });

        //mPromTitle=activity.findViewById(R.id.prom_title);
        //mPromTitle.setText("My Promotion Title");

        List<TargetRequest> requests = new ArrayList<>();
        requests.add(targetRequest1);

        // Define the profile parameters map.
        //Map<String, String> profileParameters1 = new HashMap<>();
        //profileParameters1.put("ageGroup", "20-32");

        //TargetParameters parameters = new TargetParameters.Builder().profileParameters(profileParameters1).build();
        // prep done, now retrieve content
        /*Snackbar.make(view, "Retrieving content from Target", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

        Target.retrieveLocationContent(requests, null);


        mPromBody=activity.findViewById(R.id.prom_body);
        mPromBody.setText("My Promotion Body My Promotion Body Promotion Body Promotion Body");

        activity.findViewById(R.id.surpriseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurpriseFragment();
            }
        });
    }

    private void openSurpriseFragment() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e(TAG, "Fragment activity null");
            return;
        }
        Fragment surpriseFragment = new SurpriseFragment();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_body, surpriseFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}