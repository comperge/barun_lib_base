package org.bmcoop.lib.base.sensor;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;

public class USensor {
	private USensor() {
	}

	public static ISensor with(Context pCon, int SensorManager_SENSOR_DELAY) {
		return new ISensor(pCon, SensorManager_SENSOR_DELAY);
	}

	public static ISensor withMicroSecond(Context pCon, int microSecond) {
		return new ISensor(pCon, microSecond);
	}

	public static class ISensor {
		private WSensor mISensor;

		private ISensor(Context pCon, int SensorManager_SENSOR_DELAY) {
			mISensor = new WSensor(pCon, SensorManager_SENSOR_DELAY);
		}

		public WSensor addSensor_linearAcceleration() {
			return mISensor.addSensor_linearAcceleration();
		}

		public WSensor addSensor_rotationVector() {
			return mISensor.addSensor_rotationVector();
		}

		public WSensor addSensor_accelerometer() {
			return mISensor.addSensor_accelerometer();
		}

		public WSensor addSensor_ambientTemperature() {
			return mISensor.addSensor_ambientTemperature();
		}

		public WSensor addSensor_gyroscope() {
			return mISensor.addSensor_gyroscope();
		}

		public WSensor addSensor_light() {
			return mISensor.addSensor_light();
		}

		public WSensor addSensor_magneticfield() {
			return mISensor.addSensor_magneticfield();
		}

		public WSensor addSensor_orientation() {
			return mISensor.addSensor_orientation();
		}

		public WSensor addSensor_pressure() {
			return mISensor.addSensor_pressure();
		}

		public WSensor addSensor_proximity() {
			return mISensor.addSensor_proximity();
		}

		public WSensor addSensor_relativHumidity() {
			return mISensor.addSensor_relativHumidity();
		}

		public WSensor addSensor_sandraBullock() {
			return mISensor.addSensor_gravity();
		}

		public WSensor addSensor_gameRotationVector() {
			return mISensor.addSensor_gameRotationVector();
		}

		public WSensor addSensor_geomagneticRotationVector() {
			return mISensor.addSensor_geomagneticRotationVector();
		}

		public WSensor addSensor_gyroscopeUncalibrated() {
			return mISensor.addSensor_gyroscopeUncalibrated();
		}

		public WSensor addSensor_magneticFieldUncalibrated() {
			return mISensor.addSensor_magneticFieldUncalibrated();
		}

		public WSensor addSensor_stepCounter() {
			return mISensor.addSensor_stepCounter();
		}

		public WSensor addSensor_stepDetector() {
			return mISensor.addSensor_stepDetector();
		}

		/**
		 * 호출 이후 단한번만 작동하는 트리거 이벤트입니다. getSiginificantMotion이 1값이면 움직임이 있는것입니다.
		 * 
		 * @return int
		 */
		public WSensor addSensor_significantMotion() {
			return mISensor.addSensor_significantMotion();
		}

		public WSensor addSensor_all() {
			return mISensor.addSensor_all();
		}

	}

	public static class WSensor {
		private Callback mCallback;
		private SensorManager mSensorManager;
		private SensorEnty mSensorEnty;
		private int mSensorDeilay;

		private float[] m_acc_data = null, m_mag_data = null;
		private float[] m_rotation = new float[9];
		private float[] m_result_data = new float[3];
		private boolean isRotationOn = false;

		private WSensor(Context pCon, int SensorManager_SENSOR_DELAY) {
			mSensorManager = (SensorManager) pCon.getSystemService(Context.SENSOR_SERVICE);
			mSensorDeilay = SensorManager_SENSOR_DELAY;
			mSensorEnty = new SensorEnty();
		}

		private SensorEventListener mSensorListener = new SensorEventListener() {

			public void onAccuracyChanged(Sensor sensor, int accuracy) {
			}

			public void onSensorChanged(SensorEvent event) {

				switch (event.sensor.getType()) {
					case Sensor.TYPE_ACCELEROMETER :
						mSensorEnty.setAccelerometer_x(event.values[0]);
						mSensorEnty.setAccelerometer_y(event.values[1]);
						mSensorEnty.setAccelerometer_z(event.values[2]);
						m_acc_data = event.values.clone();
						break;
					case Sensor.TYPE_MAGNETIC_FIELD :
						mSensorEnty.setMagneticfield_x(event.values[0]);
						mSensorEnty.setMagneticfield_y(event.values[1]);
						mSensorEnty.setMagneticfield_zn(event.values[2]);
						m_mag_data = event.values.clone();
						break;
					case Sensor.TYPE_PROXIMITY :
						mSensorEnty.setProximity(event.values[0]);
						break;
					case Sensor.TYPE_GYROSCOPE :
						mSensorEnty.setGyroscope_x(event.values[0]);
						mSensorEnty.setGyroscope_y(event.values[1]);
						mSensorEnty.setGyroscope_z(event.values[2]);
						break;
					case Sensor.TYPE_GRAVITY :
						mSensorEnty.setGravity_x(event.values[0]);
						mSensorEnty.setGravity_y(event.values[1]);
						mSensorEnty.setGravity_z(event.values[2]);
						break;
					case Sensor.TYPE_LINEAR_ACCELERATION :
						mSensorEnty.setLinearAcceleration_x(event.values[0]);
						mSensorEnty.setLinearAcceleration_y(event.values[1]);
						mSensorEnty.setLinearAcceleration_z(event.values[2]);
						break;
					case Sensor.TYPE_ROTATION_VECTOR :
						mSensorEnty.setRotationVector_x(event.values[0]);
						mSensorEnty.setRotationVector_y(event.values[1]);
						mSensorEnty.setRotationVector_z(event.values[2]);
						break;
					case Sensor.TYPE_AMBIENT_TEMPERATURE :
						mSensorEnty.setAmbientTemperature(event.values[0]);
						break;
					case Sensor.TYPE_PRESSURE :
						mSensorEnty.setPressure(event.values[0]);
						break;
					case Sensor.TYPE_RELATIVE_HUMIDITY :
						mSensorEnty.setRelativHumidity(event.values[0]);
						break;
					case Sensor.TYPE_LIGHT :
						mSensorEnty.setLight(event.values[0]);
						break;
					///////////////////////////////////////////////////////여기서부터 새로나온 센서 4.3 이상
					case Sensor.TYPE_GAME_ROTATION_VECTOR :
						mSensorEnty.setGameRotationVector_x(event.values[0]);
						mSensorEnty.setGameRotationVector_y(event.values[1]);
						mSensorEnty.setGameRotationVector_z(event.values[2]);
						break;
					case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR :
						mSensorEnty.setGeomagneticRotationVector_x(event.values[0]);
						mSensorEnty.setGeomagneticRotationVector_y(event.values[1]);
						mSensorEnty.setGeomagneticRotationVector_z(event.values[2]);
						break;
					case Sensor.TYPE_GYROSCOPE_UNCALIBRATED :
						mSensorEnty.setGyroscopeUncalibrated_x(event.values[0]);
						mSensorEnty.setGyroscopeUncalibrated_y(event.values[1]);
						mSensorEnty.setGyroscopeUncalibrated_z(event.values[2]);
						break;
					case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED :
						mSensorEnty.setMagneticfieldUncalibrated_x(event.values[0]);
						mSensorEnty.setMagneticfieldUncalibrated_y(event.values[1]);
						mSensorEnty.setMagneticfieldUncalibrated_z(event.values[2]);
						break;
					case Sensor.TYPE_STEP_COUNTER :
						mSensorEnty.setStepCounter(event.values[0]);
						break;
					case Sensor.TYPE_STEP_DETECTOR :
						mSensorEnty.setStepDetector(event.values[0]);
						break;
				}

				if (isRotationOn && m_acc_data != null && m_mag_data != null) {
					SensorManager.getRotationMatrix(m_rotation, null, m_acc_data, m_mag_data);// 회전매트릭스로방향데이터를얻는다.
					SensorManager.getOrientation(m_rotation, m_result_data);
					// Radian 값을 Degree 값으로 변환한다.
					m_result_data[0] = (float) Math.toDegrees(m_result_data[0]);
					// 0 이하의 값인 경우 360을 더한다.
//					if (m_result_data[0] < 0)
//						m_result_data[0] += 360;
					m_result_data[1] = (float) Math.toDegrees(m_result_data[1]);
					m_result_data[2] = (float) Math.toDegrees(m_result_data[2]);
					
					mSensorEnty.setOrientation_azimuth((int) m_result_data[0]);
					mSensorEnty.setOrientation_pitch((int) m_result_data[1]);
					mSensorEnty.setOrientation_roll((int) m_result_data[2]);
				}

				if (mCallback != null) {
					mCallback.onChanged(mSensorEnty);
				}
			}
		};

		public WSensor addSensor_accelerometer() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_magneticfield() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_orientation() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD | Sensor.TYPE_ACCELEROMETER),
					mSensorDeilay);
			isRotationOn = true;
			return this;
		}

		public WSensor addSensor_proximity() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_gyroscope() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_gravity() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_linearAcceleration() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_rotationVector() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
		public WSensor addSensor_ambientTemperature() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_pressure() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
		public WSensor addSensor_relativHumidity() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY), mSensorDeilay);
			return this;
		}

		public WSensor addSensor_light() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
		public WSensor addSensor_gameRotationVector() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.KITKAT)
		public WSensor addSensor_geomagneticRotationVector() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
		public WSensor addSensor_gyroscopeUncalibrated() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
		public WSensor addSensor_magneticFieldUncalibrated() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.KITKAT)
		public WSensor addSensor_stepCounter() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.KITKAT)
		public WSensor addSensor_stepDetector() {
			mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR), mSensorDeilay);
			return this;
		}

		@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
		public WSensor addSensor_significantMotion() {
			if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
				try {
					TriggerEventListener mTriggerEventListener = new TriggerEventListener() {
						@Override
						public void onTrigger(TriggerEvent event) {
							if (mSensorEnty == null)
								mSensorEnty = new SensorEnty();
							mSensorEnty.setSignificantMotion(1);
							if (mCallback != null) {
								mCallback.onChanged(mSensorEnty);
							}
						}
					};			
					mSensorManager.requestTriggerSensor(mTriggerEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION));
				}catch(NoSuchMethodError e){
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return this;
		}

		public WSensor addSensor_all() {
			addSensor_linearAcceleration();
			addSensor_rotationVector();
			addSensor_accelerometer();
			addSensor_ambientTemperature();
			addSensor_gyroscope();
			addSensor_light();
			addSensor_magneticfield();
			addSensor_orientation();
			addSensor_pressure();
			addSensor_proximity();
			addSensor_relativHumidity();
			addSensor_gravity();
			addSensor_gameRotationVector();
			addSensor_geomagneticRotationVector();
			addSensor_gyroscopeUncalibrated();
			addSensor_magneticFieldUncalibrated();
			addSensor_stepCounter();
			addSensor_stepDetector();
			addSensor_significantMotion();
			return this;
		}

		public WSensor forStart(Callback WSensor_Callback) {
			mCallback = WSensor_Callback;
			return this;
		}

		public void forClose() {
			if (mSensorManager != null) {
				mSensorManager.unregisterListener(mSensorListener);
			}
		}

		public static interface Callback {
			void onChanged(SensorEnty sensorEnty);
		}

	}
}