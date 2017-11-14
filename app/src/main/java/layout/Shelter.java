package layout;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hellopet.sangji.hellopet.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Shelter extends Fragment
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 15000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 15000;

    private static final LatLng kwongju = new LatLng(35.222468, 126.881656);
    private static final LatLng wulsan = new LatLng(35.391147, 129.285922);
    private static final LatLng daegu = new LatLng(36.492634, 127.280629);
    private static final LatLng phungtek = new LatLng(36.997332, 127.088349);
    private static final LatLng ansung = new LatLng(36.949117, 127.241260);
    private static final LatLng gumi = new LatLng(36.194799, 128.376251);
    private static final LatLng hankuk = new LatLng(37.871759, 126.984625);
    private static final LatLng daejeon = new LatLng(36.367250, 127.284872);
    private static final LatLng donghae = new LatLng(37.486684, 129.113623);
    private static final LatLng busan = new LatLng(35.144688, 128.930713);

    private Marker mkwongju;
    private Marker mwulsan;
    private Marker mdaegu;
    private Marker mphungtek;
    private Marker mansung;
    private Marker mgumi;
    private Marker mhankuk;
    private Marker mdaejeon;
    private Marker mdonghae;
    private Marker mbusan;


    private GoogleMap googleMap = null;
    private MapView mapView = null;
    private GoogleApiClient googleApiClient = null;
    private Marker currentMarker = null;

    float Lat, Lng;

    private final static int MAXENTRIES = 5;
    private String[] LikelyPlaceNames = null;
    private String[] LikelyAddresses = null;
    private String[] LikelyAttributions = null;
    private LatLng[] LikelyLatLngs = null;

    Document doc;


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {
        if (currentMarker != null) currentMarker.remove();

        if (location != null) {
            //현재위치의 위도 경도 가져옴
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(currentLocation);
            markerOptions.title(markerTitle);
            markerOptions.snippet(markerSnippet);
            markerOptions.draggable(true);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            currentMarker = this.googleMap.addMarker(markerOptions);

            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            return;
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = this.googleMap.addMarker(markerOptions);

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_LOCATION));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GetXMLTask task = new GetXMLTask();
        task.execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_shelter, container, false);

        mapView = (MapView) layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Location location = new Location("");
                location.setLatitude(place.getLatLng().latitude);
                location.setLongitude(place.getLatLng().longitude);

                setCurrentLocation(location, place.getName().toString(), place.getAddress().toString());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();

        if (googleApiClient != null && googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

        if (googleApiClient != null)
            googleApiClient.connect();

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();

        if (googleApiClient != null) {
            googleApiClient.unregisterConnectionCallbacks(this);
            googleApiClient.unregisterConnectionFailedListener(this);

            if (googleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
                googleApiClient.disconnect();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수
        MapsInitializer.initialize(getActivity().getApplicationContext());

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // OnMapReadyCallback implements 해야 mapView.getMapAsync(this); 사용가능. this 가 OnMapReadyCallback

        this.googleMap = googleMap;

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에 지도의 초기위치를 서울로 이동
        setCurrentLocation(null, "위치정보 가져올 수 없음", "위치 퍼미션과 GPS 활성 여부 확인하시고 자신의 위치를 찾아보십시오.");

        //나침반이 나타나도록 설정
        googleMap.getUiSettings().setCompassEnabled(true);
        // 매끄럽게 이동함
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //  API 23 이상이면 런타임 퍼미션 처리 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 사용권한체크
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

            if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {
                //사용권한이 없을경우
                //권한 재요청
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            } else {
                //사용권한이 있는경우
                if (googleApiClient == null) {
                    buildGoogleApiClient();
                }

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                }
            }
        } else {

            if (googleApiClient == null) {
                buildGoogleApiClient();
            }

            googleMap.setMyLocationEnabled(true);
        }

        mkwongju = googleMap.addMarker(new MarkerOptions()
                .position(kwongju)
                .title("광주보호소"));
        mkwongju.setTag(0);

        mwulsan = googleMap.addMarker(new MarkerOptions()
                .position(wulsan)
                .title("울산보호소"));
        mwulsan.setTag(0);

        mdaegu = googleMap.addMarker(new MarkerOptions()
                .position(daegu)
                .title("대구보호소"));
        mdaegu.setTag(0);

        mphungtek = googleMap.addMarker(new MarkerOptions()
                .position(phungtek)
                .title("평택보호소"));
        mphungtek.setTag(0);

        mansung = googleMap.addMarker(new MarkerOptions()
                .position(ansung)
                .title("안성보호소"));
        mansung.setTag(0);

        mgumi = googleMap.addMarker(new MarkerOptions()
                .position(gumi)
                .title("구미보호소"));
        mgumi.setTag(0);

        mhankuk = googleMap.addMarker(new MarkerOptions()
                .position(hankuk)
                .title("한국동물보호협회"));
        mhankuk.setTag(0);

        mdaejeon = googleMap.addMarker(new MarkerOptions()
                .position(daejeon)
                .title("대전보호소"));
        mdaejeon.setTag(0);

        mdonghae = googleMap.addMarker(new MarkerOptions()
                .position(donghae)
                .title("동해보호소"));
        mdonghae.setTag(0);

        mbusan = googleMap.addMarker(new MarkerOptions()
                .position(busan)
                .title("부산보호소"));
        mbusan.setTag(0);

    }

    private void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();
        googleApiClient.connect();
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!checkLocationServicesStatus()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("위치 서비스 비활성화");
            builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n" +
                    "위치 설정을 수정하십시오.");
            builder.setCancelable(true);
            builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent callGPSSettingIntent =
                            new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL_MS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                LocationServices.FusedLocationApi
                        .requestLocationUpdates(googleApiClient, locationRequest, this);
            }
        } else {
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(googleApiClient, locationRequest, this);

            this.googleMap.getUiSettings().setCompassEnabled(true);
            this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }

    }

    @Override
    public void onConnectionSuspended(int cause) {
        if (cause == CAUSE_NETWORK_LOST)
            Log.e(TAG, "onConnectionSuspended(): Google Play services " +
                    "connection lost.  Cause: network lost.");
        else if (cause == CAUSE_SERVICE_DISCONNECTED)
            Log.e(TAG, "onConnectionSuspended():  Google Play services " +
                    "connection lost.  Cause: service disconnected");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Location location = new Location("");
        location.setLatitude(DEFAULT_LOCATION.latitude);
        location.setLongitude((DEFAULT_LOCATION.longitude));

        setCurrentLocation(location, "위치정보 가져올 수 없음",
                "위치 퍼미션과 GPS활성 여부 확인");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "onLocationChanged call..");
        //searchCurrentPlaces();
    }

   /* private void searchCurrentPlaces() {
        @SuppressWarnings("MissingPermission")
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(googleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>(){

            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                int i = 0;
                LikelyPlaceNames = new String[MAXENTRIES];
                LikelyAddresses = new String[MAXENTRIES];
                LikelyAttributions = new String[MAXENTRIES];
                LikelyLatLngs = new LatLng[MAXENTRIES];

                for(PlaceLikelihood placeLikelihood : placeLikelihoods) {
                    LikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                    LikelyAddresses[i] = (String) placeLikelihood.getPlace().getAddress();
                    LikelyAttributions[i] = (String) placeLikelihood.getPlace().getAttributions();
                    LikelyLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                    i++;
                    if(i > MAXENTRIES - 2 ) {
                        break;
                    }
                }

                placeLikelihoods.release();

                Location location = new Location("");
                location.setLatitude(LikelyLatLngs[0].latitude);
                location.setLongitude(LikelyLatLngs[0].longitude);

                setCurrentLocation(location, LikelyPlaceNames[0], LikelyAddresses[0]);
            }
        });

    }*/

    protected static ArrayList<String> data = new ArrayList<String>();

    public class GetXMLTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try { //url로 xml을 가져온 후 문서화 정렬을 하여 준비함.
                url = new URL("http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20140301&endde=20140430&pageNo=1&numOfRows=50&ServiceKey=gGnBT%2BQpekSKFznxkX%2BszFtJyfEsf7ZqU7DAruERRLzOJ149u4Djw9Trg1MdEMHj2HiPsK2iYLFQFhF1%2BiUeRg%3D%3D");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                //Log.i("값 들어갔니?", "" + data.size());
                //Log.i("값 들어갔니?", "" + data.get(0));


            } catch (Exception e) {
                //Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }

            return doc;
        }

        @Override //xml DOM parser방식을 이용하여 노드리스트로 데이터 뿌려줌.
        protected void onPostExecute(Document doc) {
            String s = "";
            NodeList nodeList = doc.getElementsByTagName("item");
            //Log.i("정보불러와?", "" + nodeList.getLength());

            NodeList careAddr = null;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;

                /*NodeList noticeNo = fstElmnt.getElementsByTagName("noticeNo");
                s += "공고번호 = " + noticeNo.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList popfile = fstElmnt.getElementsByTagName("popfile");
                s += "사진 = " + popfile.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList kindCd = fstElmnt.getElementsByTagName("kindCd");
                s += "품종 = " + kindCd.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList colorCd = fstElmnt.getElementsByTagName("colorCd");
                s += "색상 = " + colorCd.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList sexCd = fstElmnt.getElementsByTagName("sexCd");
                s += "성별 = " + sexCd.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList neuterYn = fstElmnt.getElementsByTagName("neuterYn");
                s += "중성화 여부 = " + neuterYn.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList age = fstElmnt.getElementsByTagName("age");
                s += "나이 = " + age.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList weight = fstElmnt.getElementsByTagName("weight");
                s += "몸무게 = " + weight.item(0).getChildNodes().item(0).getNodeValue() + "(Kg)\n";

                NodeList specialMark = fstElmnt.getElementsByTagName("specialMark");
                s += "특징 = " + specialMark.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList happenDt = fstElmnt.getElementsByTagName("happenDt");
                s += "접수일 = " + happenDt.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList happenPlace = fstElmnt.getElementsByTagName("happenPlace");
                s += "발견장소 = " + happenPlace.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList noticeSdt = fstElmnt.getElementsByTagName("noticeSdt");
                s += "공고기간 = " + noticeSdt.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList careNm = fstElmnt.getElementsByTagName("careNm");
                s += "보호센터이름 = " + careNm.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList careTel = fstElmnt.getElementsByTagName("careTel");
                s += "보호센터 전화번호 = " + careTel.item(0).getChildNodes().item(0).getNodeValue() + "\n";*/

                careAddr = fstElmnt.getElementsByTagName("careAddr");
                s += "보호센터 주소 = " + careAddr.item(0).getChildNodes().item(0).getNodeValue() + "\n";
                //Log.i("값 나오니?", "" + careAddr.getLength());

               /* NodeList orgNm = fstElmnt.getElementsByTagName("orgNm");
                s += "보호소 관할기관 = " + orgNm.item(0).getChildNodes().item(0).getNodeValue() + "\n\n";

                NodeList chargeNm = fstElmnt.getElementsByTagName("chargeNm");
                s += "담당자 = " + chargeNm.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList officetel = fstElmnt.getElementsByTagName("officetel");
                s += "담당자 전화번호 = " + officetel.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList noticeComment = fstElmnt.getElementsByTagName("noticeComment");
                s += "특이사항 = " + noticeComment.item(0).getChildNodes().item(0).getNodeValue() + "\n";*/


                data.add(careAddr.item(0).getChildNodes().item(0).getNodeValue());
                Log.i("주소값 들어갔니?", "" + data.get(i));

            }

            //textview.setText(s); //어디에 보여줄거?

            super.onPostExecute(doc);


        }
    }

    /*public static Location findGeoPoint(Context mcontext, String address) {

        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정
        data = null;

        try {
            addr = coder.getFromLocationName(address, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < data.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                Log.i("결과값", "" + loc.getLatitude());
                Log.i("결과값2", "" + loc.getLongitude());
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;

    }*/


    public Shelter() {
        // Required empty public constructor
    }

    public static Shelter newInstance() {

        Bundle args = new Bundle();

        Shelter fragment = new Shelter();
        fragment.setArguments(args);

        return fragment;
    }
}