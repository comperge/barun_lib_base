package org.bmcoop.lib.base.util;
//package org.bmcoop.lib.base.util;
//
//import org.bmcoop.lib.base.util.UTimer.WTimerOnce;
//
//import android.content.Context;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
//import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
//import com.google.android.gms.location.LocationClient;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.maps.model.LatLng;
//
//public class ULocation {
//	private CallBack mCallBack;
//	private static Location mLastLocation;
//	private static int mStayZone = 0; // 0.unknown, 1.moving, 2.stayzone
//	// private static long mStayZoneStartTime = 0;
//	private static LatLng mStayZoneLatLng;
//
//	private ULocation() {
//	}
//
//	/**
//	 * @param pCon
//	 *            minSec : 10초, minDistance : 1000미터
//	 * @return
//	 */
//	public static WLocation withNetwork(Context pCon) {
//		return new ULocation().get(pCon, 10, 1000);
//	}
//
//	public static WLocation withNetwork(Context pCon, int minSec, int minDistance) {
//		return new ULocation().get(pCon, minSec, minDistance);
//	}
//
//	private WLocation get(Context pCon, int minSec, int minDistance) {
//		return new WLocation(pCon, minSec, minDistance);
//	}
//
//	public class WLocation {
//		private Context mCon;
//		private LocationClient mLocationClient;
//		private LocationRequest mLocationRequest;
//		private WTimerOnce mStayZoneTimer;
//
//		private WLocation(Context pCon, int minSec, int minDistance) {
//			mCon = pCon;
//			mLocationRequest = LocationRequest.create();
//			mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//			// locationRequest.setExpirationDuration(1000); // 밀리 초 단위로 요청의 기간을
//			// 설정합니다.
//			// locationRequest.setExpirationTime(1000); // 부팅 이후 밀리 초에 요청 만료 시간을
//			// 설정합니다.
//			// locationRequest.setFastestInterval(1000); // 명시적으로 밀리 초 단위로 위치
//			// 업데이트에 대한 빠른 간격을 설정합니다.
//			// locationRequest.setNumUpdates(1); // 위치 업데이트의 수를 설정합니다.
//			mLocationRequest.setInterval(minSec);
//			mLocationRequest.setSmallestDisplacement(minDistance);
//
//			mLocationClient = new LocationClient(mCon, new ConnectionCallbacks() {
//				@Override
//				public void onConnected(Bundle bundle) {
//					if (mLastLocation == null) {
//						mLastLocation = mLocationClient.getLastLocation();
//					}
//					mLocationClient.requestLocationUpdates(mLocationRequest, mLocationListener);
//				}
//
//				@Override
//				public void onDisconnected() {
//				}
//			}, new OnConnectionFailedListener() {
//				@Override
//				public void onConnectionFailed(ConnectionResult connectionResult) {
//					if (mCallBack != null) {
//						mCallBack.onConnectionFailed(connectionResult);
//					}
//				}
//			});
//		}
//
//		public WLocation forStart(CallBack ULocation_CallBack) {
//			mCallBack = ULocation_CallBack;
//			mLocationClient.connect();
//
//			mStayZone = 0;
//
//			return this;
//		}
//
//		// 5분초과하면 여기에 옴.
//		WTimerOnce.Callback mStayZoneTimerCallback = new WTimerOnce.Callback() {
//			@Override
//			public void onEnd() {
//				if (mLastLocation == null) {
//					mStayZone = 0; // 0.unknown
//				} else {
//					if (mStayZoneLatLng == null) {
//						mStayZone = 0; // 0.unknown
//					} else {
//						mStayZone = 2; // 2.stayzone
//					}
//				}
//			}
//		};
//
//		LocationListener mLocationListener = new LocationListener() {
//			@Override
//			public void onLocationChanged(Location location) {
//				Log.d("ULocation", "mLocationListener  onLocationChanged   location.getTime():" + location.getTime());
//				boolean isOffLocation = false;
//				if (mCallBack != null) {
//
//					if (mStayZoneLatLng == null) {
//						mStayZone = 0; // 0.unknown
//						mStayZoneLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//
//						if (mStayZoneTimer != null) {
//							mStayZoneTimer.forClose();
//							mStayZoneTimer = null;
//						}
//						mStayZoneTimer = UTimer.withOnce(5 * 60).forStart_autoClose(mStayZoneTimerCallback);
//					} else {
//						if (!isCheckStayZone(location)) {
//							mStayZone = 1; // 1.moving
//							mStayZoneLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//
//							if (mStayZoneTimer != null) {
//								mStayZoneTimer.forClose();
//								mStayZoneTimer = null;
//							}
//							mStayZoneTimer = UTimer.withOnce(5 * 60).forStart_autoClose(mStayZoneTimerCallback);
//						}
//					}
//
//					if (mLastLocation != null) {
//						if (location.getBearing() == 0.0f) {
//							location.setBearing(location.bearingTo(mLastLocation));
//						}
//						double distance = location.distanceTo(mLastLocation);
//						Bundle bundle = new Bundle();
//						bundle.putInt("distance", (int) distance);
//
//						int gapSecond = UCalendar.with(UCalendar.with(mLastLocation.getTime()).time(), UCalendar.with(location.getTime()).time()).secondGap();
//
//						// speed( meters/second )
//						if (location.getSpeed() == 0.0f) {
//							double vel = 0.0;
//							if (gapSecond != 0) {
//								// vel = ((double) distance * 3600.0) /
//								// ((double) gapSecond * 1000.0);
//								vel = (double) (distance / gapSecond);
//							}
//							location.setSpeed((float) vel);
//						} else {
//							// location.setSpeed((float) (gapSecond * 3600.0 /
//							// 1000.0));
//						}
//
//						// speed가 (360Km/h(1초에 100m)이동하면 뜀으로 본다.)
//						// if (location.getSpeed() > 100) {
//						// isOffLocation = true;
//						// }
//					}
//
//					if (!isOffLocation) {
//						mLastLocation = location;
//						mCallBack.onLocationChanged(location);
//					}
//				}
//			}
//		};
//
//		private boolean isCheckStayZone(Location location) {
//			final double RADIUS_TOLERANCE = 0.015; // 반경 오차범위
//
//			boolean checked = false;
//			if (mStayZoneLatLng != null) {
//				if ((mStayZoneLatLng.latitude - RADIUS_TOLERANCE) < location.getLatitude()
//						&& (mStayZoneLatLng.longitude - RADIUS_TOLERANCE) < location.getLongitude()
//						&& (mStayZoneLatLng.latitude + RADIUS_TOLERANCE) > location.getLatitude()
//						&& (mStayZoneLatLng.longitude + RADIUS_TOLERANCE) > location.getLongitude()) {
//					checked = true;
//				}
//			}
//			return checked;
//		}
//
//		public void forClose() {
//			try {
//				mLocationClient.removeLocationUpdates(mLocationListener);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		public Location getLastLocation() {
//			// if (mLocationClient != null && mLocationClient.isConnected()) {
//			// return mLocationClient.getLastLocation();
//			// }
//			// if (mLastLocation == null) {
//			// mLastLocation = new Location("fused");
//			// mLastLocation.setLatitude(Double.valueOf("37.5662548"));
//			// mLastLocation.setLongitude(Double.valueOf("126.9852141"));
//			// mLastLocation.setTime(UCalendar.with().time_long());
//			// mLastLocation.setAltitude(Double.valueOf("0.0"));
//			// mLastLocation.setSpeed(Float.valueOf("0.0"));
//			// mLastLocation.setBearing(Float.valueOf("0.0"));
//			// mLastLocation.setAccuracy(Float.valueOf("0.0"));
//			// }
//			return mLastLocation;
//		}
//
//		public int getStayZone() {
//			return mStayZone;
//		}
//
//		public LatLng getStayZoneLatLng() {
//			return mStayZoneLatLng;
//		}
//	}
//
//	public interface CallBack {
//		void onLocationChanged(Location location);
//
//		void onConnectionFailed(ConnectionResult connectionResult);
//	}
//}
