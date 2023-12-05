package com.example.cncdhumanedcandroid.UIs;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.cncdhumanedcandroid.OfflineDb.Helper.RealmDatabaseHelper;
import com.example.cncdhumanedcandroid.Session.SessionManager;
import com.example.cncdhumanedcandroid.Utils.CustomDialogHelper;
import com.example.cncdhumanedcandroid.Utils.WebViewJavascriptCode;
import com.example.cncdhumanedcandroid.ViewModels.WebViewModel;
import com.example.cncdhumanedcandroid.databinding.ActivityWebViewBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class ActivityWebView extends AppCompatActivity {

    ActivityWebViewBinding activityWebViewBinding;

    SessionManager sessionManager;

    private ValueCallback<Uri> mUploadMessage;

    private static final int FILECHOOSER_RESULTCODE = 5173;

    private PermissionRequest myRequest;
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    RealmDatabaseHelper realmDatabaseHelper;

    private String formName;

    Realm realm;
    private final static int FCR = 1;

    private String formId = "";

    private String formdata = "";

    String mode = "";

    private String entityId = "";

    String formJson = "{}";

    CustomDialogHelper customDialogHelper;

    private FusedLocationProviderClient locationProviderClient;
    private LocationCallback locationCallback;

    ArrayList<String> locationCoordinates = new ArrayList<>();

    MutableLiveData<Boolean> isEndCoordinatesObtained = new MutableLiveData<>();

    WebViewJavascriptCode webViewJavascriptCode;

    WebViewModel webViewModel;


    @Override
    public void onBackPressed() {
        if (mode.equals("readOnly")) {
            finish();
        } else {
            goback();
        }
    }


    String questionairID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWebViewBinding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(activityWebViewBinding.getRoot());
        sessionManager = new SessionManager(this);
        customDialogHelper = new CustomDialogHelper(ActivityWebView.this);
        realmDatabaseHelper = new RealmDatabaseHelper(this);
        realmDatabaseHelper.InitializeRealm(this);
        setUpLocation();
        LoadWebViewWithDifferentSettings();
        LocationUpdates();
        webViewJavascriptCode = new WebViewJavascriptCode();
        webViewModel = new ViewModelProvider(ActivityWebView.this).get(WebViewModel.class);

        activityWebViewBinding.surveyWebView.setVisibility(View.GONE);
        activityWebViewBinding.plzwaittext.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                activityWebViewBinding.plzwaittext.setVisibility(View.GONE);
                activityWebViewBinding.surveyWebView.setVisibility(View.VISIBLE);
            }
        }, 2000);

    }

    public String JsonToInject(String json) {

        Log.d("constants.Tag", json);
        JsonObject parsedjson;
        if (!json.equals("")) {
            parsedjson = (JsonObject) new JsonParser().parse(json);
        } else {
            parsedjson = (JsonObject) new JsonParser().parse("{}");
        }

        Log.d("parsedjson", parsedjson.toString());

        if (parsedjson.toString().equals("")) {

            return "{}";
        } else {

            return parsedjson.toString();
        }
    }

    public String getSurveyReadOnly(String id) {
        String formjson = realmDatabaseHelper.readDataSurvey(id);
        String json = formjson;
        int charactersToRemove = 1;

        String stringWithoutStart = json.substring(charactersToRemove).substring(0, json.length() - charactersToRemove);
        String finalString = stringWithoutStart.substring(0, stringWithoutStart.length() - charactersToRemove);

        Log.d("constants.Tag", finalString);
        Log.d("constants.info" + "function", formjson);
        Log.d("constants.Tag", entityId);
        Log.d("form", formjson);

        String form = "{" + finalString + ",\n" + "\"mode\": \"display\"}";

        Log.d("Tag1", form);
        return form.toString();
    }

    public String getSurveyFormData(String id) {
        String formjson = realmDatabaseHelper.readDataSurvey(id);

        Log.d("constants.info" + "function", formjson);
//        Log.d(constants.Tag, entityId);
        Log.d("form", formjson);
        return formjson;
    }

    public String getFormEntity(String key) {

        Gson gson = new Gson();
        String object =getEntities();
        JsonObject jsonObject = gson.fromJson(object, JsonObject.class);
        String firstformId = jsonObject.get(key).getAsString();
        entityId = firstformId;
        Log.d("fromObject", String.valueOf(object));
        Log.d("constants.Tag" + "key", entityId);
        return firstformId;


    }

    public void LoadJavaScriptReadOnly(String formjson, String formData) {

        String jscode = webViewJavascriptCode.provideJavaScriptCodeReadOnly(formjson, formData);
        activityWebViewBinding.surveyWebView.evaluateJavascript(jscode, null);
    }

    public void LoadJavaScriptEditable(String formjson, String function, String formData) {

        String jscode = webViewJavascriptCode.ProvideJavascriptEditableCode(formjson, function, formData);
        activityWebViewBinding.surveyWebView.evaluateJavascript(jscode, null);
    }

    public void Loadgetjavascript(String formjson, String function) {


        String jscode = webViewJavascriptCode.provideJavascriptCode(formjson, function);
        activityWebViewBinding.surveyWebView.evaluateJavascript(jscode, null);


    }


    public void setUpWebView(String key, String function) {
        Log.d("setUpWebView", "setUpWebView");

        activityWebViewBinding.surveyWebView.getSettings().setJavaScriptEnabled(true);
        activityWebViewBinding.surveyWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        activityWebViewBinding.surveyWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        activityWebViewBinding.surveyWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        activityWebViewBinding.surveyWebView.getSettings().setDomStorageEnabled(true);
        activityWebViewBinding.surveyWebView.getSettings().setAllowFileAccess(true);
        activityWebViewBinding.surveyWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        activityWebViewBinding.surveyWebView.getSettings().setDomStorageEnabled(true);

        activityWebViewBinding.surveyWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");


        activityWebViewBinding.surveyWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("pagefinish", "pagefinish");

                if (mode.equals("readOnly")) {
                    LoadJavaScriptReadOnly(JsonToInject(getSurveyReadOnly(getFormEntity(key))), formJson);
                    Log.d("webViewmode", mode);
                } else if (mode.equals("edit")) {
                    LoadJavaScriptEditable(JsonToInject(getSurveyFormData(getFormEntity(key))), function, formJson);
                    Log.d("webViewmode", mode);
                } else {
                    Loadgetjavascript(JsonToInject(getSurveyFormData(getFormEntity(key))), function);
                    Log.d("webViewKey", key);
                }

//                if (!sessionManager.getthemstate()) {
////                    Log.d("key",key);
//                    // injectCities();
//
//                } else {
//
//                    ForceWebViewToDarkMode2(JsonToInject((JsonToInject(getSurveyFormData(getFormEntity(key))))), function);
//                    InjectDarkMode();
//                }
            }
        });

        activityWebViewBinding.surveyWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Logger.i("onPermissionRequest");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Logger.i("Grant", request.toString());
                    Logger.i("getOrigin", request.getOrigin().toString());
                    request.grant(request.getResources());
                }
                if (request.getOrigin().toString().equals("file:///")) {
                    Logger.i("GRANTED", "GRANTED");
                } else {
                    Log.d("DENIED", "DENIED");
                    request.deny();
                }
                myRequest = request;
            }


            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i("WebView Log: ", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                Logger.i("WebView Log: ", consoleMessage.message());
                Logger.i("WebView Log:", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                return true;
            }

            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                this.openFileChooser(uploadMsg, "*/*");
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                this.openFileChooser(uploadMsg, acceptType, null);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            public File createImageFile() throws IOException {
                // Create an image file name based on the current timestamp
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "IMG_" + timeStamp + ".jpg";
                Logger.i("1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣");
                // Get the directory where the image file will be stored (e.g., Pictures directory)
                File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "CNCDImages");


                Logger.i("StorageDir 🤚🤚🤚🤚🤚🤚🤚🤚🤚", storageDir);
                // If the directory doesn't exist, create it
                if (!storageDir.exists()) {
                    Logger.i("2️2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣⃣");
                    storageDir.mkdirs();
                }

                // Create the image file
                File imageFile = new File(storageDir, imageFileName);

                return imageFile;
            }

            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

                if (mUMA != null) {
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(ActivityWebView.this.getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        //photoFile = 'Utility.createImageFile()';
                        photoFile = createImageFile();
                        Logger.i("Image-File-Path", photoFile.getAbsolutePath());

                        //  imagestoragepath.add(photoFile.getAbsolutePath());
                        Logger.i("filechooserparams", String.valueOf(fileChooserParams));
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    } catch (Exception ex) {
                        Log.e("TAG", "Image file creation failed", ex);
                        Logger.i("Image-File-creation-failed", ex.toString());
                    }
                    if (photoFile != null) {
                        mCM = "file:" + photoFile.getAbsolutePath();
                        //  imagestoragepath.add(photoFile.getAbsolutePath());
                        Logger.i("Image-File-Path", photoFile.getAbsolutePath());
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }

                Intent contentSelectionIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }

        });

//        webViewSurveyFormBinding.surveyWebView.loadUrl("file:///android_asset/html/index.html");
        //webViewSurveyFormBinding.surveyWebView.loadUrl("https://www.google.com");
        loadHtmlFromAssets();

    }

    private void loadHtmlFromAssets() {
        // Specify the base URL as "file:///android_asset/" to load assets
        String baseUrl = "file:///android_asset/basic/";
//        String webviewbaseurl = BuildConfig.API_BASE_URL;
        String webviewbaseurl = "http://192.168.20.182:8888/api/v1/";

        // Load the HTML file from assets
        String htmlFileName = "index.html"; // Replace with your HTML file name
        String mimeType = "text/html";
        String encoding = "UTF-8";

//        // Construct the full path to the HTML file in assets
//        String htmlFilePath = baseUrl+ htmlFileName;
//
//        try {
//            InputStream inputStream = getAssets().open(htmlFilePath);
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//
//            String htmlContent = new String(buffer, encoding);

        String html = loadHtmlFromAssets(this, "basic/index.html");

        // Load the HTML content into the WebView with a base URL
        //  webView.loadDataWithBaseURL(baseUrl, htmlContent, mimeType, encoding, null);
        Log.d("html", html);
        activityWebViewBinding.surveyWebView.loadDataWithBaseURL(webviewbaseurl, html, mimeType, encoding, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Load the HTML content into the WebView

    }

    public static String loadHtmlFromAssets(Context context, String filename) {
        AssetManager assetManager = context.getAssets();
        String htmlContent = "";

        try {
            InputStream inputStream = assetManager.open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            htmlContent = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlContent;
    }

    public String getEntities() {
        return realmDatabaseHelper.getFormConfig();
    }


    String getTimeStamp(String msg) {

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssa");
        String stamp = sdf.format(date);
        Log.d("tag", msg + stamp);
        return stamp;
    }

    public void getcurrentlocationstart() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, new CancellationToken() {
            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }

            @Override
            public boolean isCancellationRequested() {
                return false;
            }


        }).addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {


                    sessionManager.saveStartCoordinatesAndTime(location.getLatitude(), location.getLongitude(), getTimeStamp("start-"));
                    Log.d("constants.Tag", String.valueOf(location.getLatitude()));
                    Log.d("constants.Tag", String.valueOf(location.getLongitude()));


                }
            }
        });
    }

    public class EndLocationAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("constants.Tag", "getting end location");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getcurrentlocationstarEnd();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            if (locationCoordinates.size() != 0) {

                isEndCoordinatesObtained.setValue(true);
            } else {

                isEndCoordinatesObtained.setValue(false);
            }

            Log.d("constants.Tag", "getting end location exited" + isEndCoordinatesObtained.getValue().toString());
        }


        public void getcurrentlocationstarEnd() {


            if (ActivityCompat.checkSelfPermission(ActivityWebView.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityWebView.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }


            locationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, new CancellationToken() {
                @NonNull
                @Override
                public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                    return null;
                }

                @Override
                public boolean isCancellationRequested() {
                    return false;
                }


            }).addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {

                        locationCoordinates.add(String.valueOf(location.getLatitude()));
                        locationCoordinates.add(String.valueOf(location.getLongitude()));


                    }
                }
            });


        }
    }

    public void setUpLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void LocationUpdates() {

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {


                    Log.d("lat", String.valueOf(location.getLatitude()));
                    Log.d("lon", String.valueOf(location.getLatitude()));
                }
            }
        };
    }

    public void LoadWebViewWithDifferentSettings() {
        Bundle extras = getIntent().getExtras();
        formId = extras.getString("formID");

        Log.d("constants.Tag", formId);

        getcurrentlocationstart();
        if (!formId.isEmpty()) {
            sessionManager = new SessionManager(this);
            CheckLocationTurnedOn();
            customDialogHelper = new CustomDialogHelper(ActivityWebView.this);
            setUpLocation();
            setUpWebView(formId, "Android.submitForm(results, myJsonString)");

        }

        else {
            android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
            alertDialog.setTitle("Error Loading Form");
            alertDialog.setMessage("Do you wish to exit the App");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }

            });

            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            });
            alertDialog.show();
        }


    }

    public void CheckLocationTurnedOn() {

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

            Log.d("error", ex.getMessage());
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {

            Log.d("error", ex.getMessage());

        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            new AlertDialog.Builder(ActivityWebView.this).setMessage("Location not turned on please turn on location to connect bluetooth printer").setPositiveButton("Turn on", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                        }
                    }).setCancelable(false)


                    .show();
        }
    }

    public void createCompletedJson() {

        ArrayList<String> arrayList = realmDatabaseHelper.readCompletedForm();

        if (arrayList.size() != 0) {
            Gson gson = new Gson();
            String jsonArrayString = gson.toJson(arrayList);
            JsonObject response = new JsonObject();
            response.addProperty("id", arrayList.get(8).toString());
            response.addProperty("start_time", arrayList.get(0));
            response.addProperty("end_time", arrayList.get(1));
            response.addProperty("appversion", arrayList.get(2));
            response.addProperty("FormName", formName);
            response.addProperty("start_coordinates_latitude", arrayList.get(3));
            response.addProperty("start_coordinates_longitude", arrayList.get(4));
            response.addProperty("end_coordinates_latitude", arrayList.get(5));
            response.addProperty("end_coordinates_longitude", arrayList.get(6));
            response.addProperty("form_data", arrayList.get(7));
            Log.d("constants.info", response.toString());
        }

    }

    public void goback() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setMessage(R.string.formcancelmessage);
        alert.setMessage("Do you want to close the form?");
        alert.setTitle("Form Cancellation");


        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }
        });

        alert.show();


    }

    class JavaScriptInterface {
        Context context;

        public JavaScriptInterface(Context context) {

            this.context = context;
        }

        @JavascriptInterface
        public void ShowProgressDialog() {

            customDialogHelper.showCustomProgressDialog();
        }

        @JavascriptInterface
        public void dissmissdialog() {

            customDialogHelper.dismissDialog();
        }

        @JavascriptInterface
        public void getSubmittedData(String data) {

            Log.d("constants.info", "Submit function called");
            customDialogHelper.dismissDialog();
            formdata = data;
            String form_start_time = sessionManager.getStartTimestamp();
            String form_end_time = getTimeStamp("end-");
            String start_latitude = sessionManager.getLatitudeStart();
            String start_longitude = sessionManager.getLongitudeStart();
            String end_latitude = "0.0";
            String end_longitude = "0.0";
            String appversion = "";

            try {
                appversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException nameNotFoundException) {

                Log.d("package", nameNotFoundException.getMessage().toString());
            }

            realmDatabaseHelper.InsertCompletedForm(Integer.parseInt(formId), form_start_time, form_end_time, start_latitude, start_longitude, end_latitude, end_longitude, appversion, data);
            Log.d("constants.info", formdata);
            Log.d("constants.info", appversion);
            Log.d("constants.info", formId.toString());


        }

        @JavascriptInterface
        public void submitForm(String formjson, String imageurls) {


            if (imageurls.equals("[]")) {


     /*           //post data json way
                Log.d(constants.info,"post called");
                customDialogHelper.dismissDialog();


                try {
                    appVersion = getPackageManager()
                            .getPackageInfo(getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RuntimeException(e);
                }


                String locationCoordinate = latStart+","+lonStart;
                String formJSON = formjson;

                String interviewtakenAt = getTimeStamp("Interview_taken_at-");
                String interviewTimeStart = sessionManager.getStartTimestamp();
                String interviewTimeEnd = getTimeStamp("end-");


                String locationCoordinatesStart = sessionManager.getLatitudeStart() + "," +
                        sessionManager.getLongitudeStart();


                String locationCoordinatesEnd = latStart+","+lonStart;

                if (imageurls.equals("[]")){
                    surveyViewModel.PostFarmerMedicalFormData(context,
                            questionnaireID,
                            farmId,
                            farmerId,
                            appVersion,
                            locationCoordinate,
                            formJSON,
                            interviewtakenAt,
                            interviewTimeStart,
                            interviewTimeEnd,
                            locationCoordinatesStart,
                            locationCoordinatesEnd,
                            imagestoragepath.toString()

                    );*/

                finish();


            } else {

/*                //post data multipart way
                JSONArray ImagesArray = convertStringToJsonArray(imageurls);
                List<MultipartBody.Part> imageParts = new ArrayList<>();

                for (int i = 0; i < ImagesArray.length(); i++) {
                    // Example: Accessing image_name and image_path for each object
                    try {
                        String imageName = ImagesArray.getJSONObject(i).getString("image_name");
                        String imagePath = ImagesArray.getJSONObject(i).getString("image_path");
                        Log.d("Image Name: ", imageName);
                        Log.d("Image Path: ", imagePath);

                        RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), imageArray.get(i));
                        MultipartBody.Part imagePart = MultipartBody.Part.createFormData(imageName, imageArray.get(i).getName(), imageRequestBody);
                        imageParts.add(imagePart);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                surveyViewModel.PostFarmerMedicalFormDataMultipart(
                        imageParts,
                        context,
                        questionnaireID,
                        farmId,
                        farmerId,
                        appVersion,
                        locationCoordinate,
                        formJSON,
                        interviewtakenAt,
                        interviewTimeStart,
                        interviewTimeEnd,
                        locationCoordinatesStart,
                        locationCoordinatesEnd,
                        imagestoragepath.toString()
                );*/
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "on destroy called");
    }


}