package org.bmcoop.lib.base.sensor;

import java.io.Serializable;

public class SensorEnty implements Serializable {
	
	private static final long serialVersionUID = -5493420235862510902L;
	private float accelerometer_x;
	private float accelerometer_y;
	private float accelerometer_z;
	private float magneticfield_x;
	private float magneticfield_y;
	private float magneticfield_zn;
	private float proximity;
	private float gyroscope_x;
	private float gyroscope_y;
	private float gyroscope_z;

	private float gravity_x;
	private float gravity_y;
	private float gravity_z;
	private float linearAcceleration_x;
	private float linearAcceleration_y;
	private float linearAcceleration_z;
	private float rotationVector_x;
	private float rotationVector_y;
	private float rotationVector_z;
	private float ambientTemperature;
	private float pressure;
	private float relativHumidity;
	private float light;
	private float orientation_azimuth;
	private float orientation_pitch;
	private float orientation_roll;

	//////////////// 여기서부터 새로나온 센서 4.3 부터
	private float gameRotationVector_x;
	private float gameRotationVector_y;
	private float gameRotationVector_z;
	private float geomagneticRotationVector_x;
	private float geomagneticRotationVector_y;
	private float geomagneticRotationVector_z;
	private float gyroscopeUncalibrated_x;
	private float gyroscopeUncalibrated_y;
	private float gyroscopeUncalibrated_z;
	private float magneticfieldUncalibrated_x;
	private float magneticfieldUncalibrated_y;
	private float magneticfieldUncalibrated_z;
	private float stepCounter;
	private float stepDetector;
	private int significantMotion;

	public float getGeomagneticRotationVector_x() {
		return geomagneticRotationVector_x;
	}

	public void setGeomagneticRotationVector_x(float geomagneticRotationVector_x) {
		this.geomagneticRotationVector_x = geomagneticRotationVector_x;
	}

	public float getGeomagneticRotationVector_y() {
		return geomagneticRotationVector_y;
	}

	public void setGeomagneticRotationVector_y(float geomagneticRotationVector_y) {
		this.geomagneticRotationVector_y = geomagneticRotationVector_y;
	}

	public float getGeomagneticRotationVector_z() {
		return geomagneticRotationVector_z;
	}

	public void setGeomagneticRotationVector_z(float geomagneticRotationVector_z) {
		this.geomagneticRotationVector_z = geomagneticRotationVector_z;
	}

	public float getGyroscopeUncalibrated_x() {
		return gyroscopeUncalibrated_x;
	}

	public void setGyroscopeUncalibrated_x(float gyroscopeUncalibrated_x) {
		this.gyroscopeUncalibrated_x = gyroscopeUncalibrated_x;
	}

	public float getGyroscopeUncalibrated_y() {
		return gyroscopeUncalibrated_y;
	}

	public void setGyroscopeUncalibrated_y(float gyroscopeUncalibrated_y) {
		this.gyroscopeUncalibrated_y = gyroscopeUncalibrated_y;
	}

	public float getGyroscopeUncalibrated_z() {
		return gyroscopeUncalibrated_z;
	}

	public void setGyroscopeUncalibrated_z(float gyroscopeUncalibrated_z) {
		this.gyroscopeUncalibrated_z = gyroscopeUncalibrated_z;
	}

	public float getMagneticfieldUncalibrated_x() {
		return magneticfieldUncalibrated_x;
	}

	public void setMagneticfieldUncalibrated_x(float magneticfieldUncalibrated_x) {
		this.magneticfieldUncalibrated_x = magneticfieldUncalibrated_x;
	}

	public float getMagneticfieldUncalibrated_y() {
		return magneticfieldUncalibrated_y;
	}

	public void setMagneticfieldUncalibrated_y(float magneticfieldUncalibrated_y) {
		this.magneticfieldUncalibrated_y = magneticfieldUncalibrated_y;
	}

	public float getMagneticfieldUncalibrated_z() {
		return magneticfieldUncalibrated_z;
	}

	public void setMagneticfieldUncalibrated_z(float magneticfieldUncalibrated_z) {
		this.magneticfieldUncalibrated_z = magneticfieldUncalibrated_z;
	}

	public SensorEnty() {
	}

	public float getAccelerometer_x() {
		return accelerometer_x;
	}

	public void setAccelerometer_x(float accelerometer_x) {
		this.accelerometer_x = accelerometer_x;
	}

	public float getAccelerometer_y() {
		return accelerometer_y;
	}

	public void setAccelerometer_y(float accelerometer_y) {
		this.accelerometer_y = accelerometer_y;
	}

	public float getAccelerometer_z() {
		return accelerometer_z;
	}

	public void setAccelerometer_z(float accelerometer_z) {
		this.accelerometer_z = accelerometer_z;
	}

	public float getMagneticfield_x() {
		return magneticfield_x;
	}

	public void setMagneticfield_x(float magneticfield_x) {
		this.magneticfield_x = magneticfield_x;
	}

	public float getMagneticfield_y() {
		return magneticfield_y;
	}

	public void setMagneticfield_y(float magneticfield_y) {
		this.magneticfield_y = magneticfield_y;
	}

	public float getMagneticfield_zn() {
		return magneticfield_zn;
	}

	public void setMagneticfield_zn(float magneticfield_zn) {
		this.magneticfield_zn = magneticfield_zn;
	}

	public float getProximity() {
		return proximity;
	}

	public void setProximity(float proximity) {
		this.proximity = proximity;
	}

	public float getGyroscope_x() {
		return gyroscope_x;
	}

	public void setGyroscope_x(float gyroscope_x) {
		this.gyroscope_x = gyroscope_x;
	}

	public float getGyroscope_y() {
		return gyroscope_y;
	}

	public void setGyroscope_y(float gyroscope_y) {
		this.gyroscope_y = gyroscope_y;
	}

	public float getGyroscope_z() {
		return gyroscope_z;
	}

	public void setGyroscope_z(float gyroscope_z) {
		this.gyroscope_z = gyroscope_z;
	}

	public float getGravity_x() {
		return gravity_x;
	}

	public void setGravity_x(float gravity_x) {
		this.gravity_x = gravity_x;
	}

	public float getGravity_y() {
		return gravity_y;
	}

	public void setGravity_y(float gravity_y) {
		this.gravity_y = gravity_y;
	}

	public float getGravity_z() {
		return gravity_z;
	}

	public void setGravity_z(float gravity_z) {
		this.gravity_z = gravity_z;
	}

	public float getLinearAcceleration_x() {
		return linearAcceleration_x;
	}

	public void setLinearAcceleration_x(float linearAcceleration_x) {
		this.linearAcceleration_x = linearAcceleration_x;
	}

	public float getLinearAcceleration_y() {
		return linearAcceleration_y;
	}

	public void setLinearAcceleration_y(float linearAcceleration_y) {
		this.linearAcceleration_y = linearAcceleration_y;
	}

	public float getLinearAcceleration_z() {
		return linearAcceleration_z;
	}

	public void setLinearAcceleration_z(float linearAcceleration_z) {
		this.linearAcceleration_z = linearAcceleration_z;
	}

	public float getRotationVector_x() {
		return rotationVector_x;
	}

	public void setRotationVector_x(float rotationVector_x) {
		this.rotationVector_x = rotationVector_x;
	}

	public float getRotationVector_y() {
		return rotationVector_y;
	}

	public void setRotationVector_y(float rotationVector_y) {
		this.rotationVector_y = rotationVector_y;
	}

	public float getRotationVector_z() {
		return rotationVector_z;
	}

	public void setRotationVector_z(float rotationVector_z) {
		this.rotationVector_z = rotationVector_z;
	}

	public float getAmbientTemperature() {
		return ambientTemperature;
	}

	public void setAmbientTemperature(float ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public float getRelativHumidity() {
		return relativHumidity;
	}

	public void setRelativHumidity(float relativHumidity) {
		this.relativHumidity = relativHumidity;
	}

	public float getLight() {
		return light;
	}

	public void setLight(float light) {
		this.light = light;
	}

	public float getOrientation_azimuth() {
		return orientation_azimuth;
	}

	public void setOrientation_azimuth(float orientation_azimuth) {
		this.orientation_azimuth = orientation_azimuth;
	}

	public float getOrientation_pitch() {
		return orientation_pitch;
	}

	public void setOrientation_pitch(float orientation_pitch) {
		this.orientation_pitch = orientation_pitch;
	}

	public float getOrientation_roll() {
		return orientation_roll;
	}

	public void setOrientation_roll(float orientation_roll) {
		this.orientation_roll = orientation_roll;
	}

	public float getGameRotationVector_x() {
		return gameRotationVector_x;
	}

	public void setGameRotationVector_x(float gameRotationVector_x) {
		this.gameRotationVector_x = gameRotationVector_x;
	}

	public float getGameRotationVector_y() {
		return gameRotationVector_y;
	}

	public void setGameRotationVector_y(float gameRotationVector_y) {
		this.gameRotationVector_y = gameRotationVector_y;
	}

	public float getGameRotationVector_z() {
		return gameRotationVector_z;
	}

	public void setGameRotationVector_z(float gameRotationVector_z) {
		this.gameRotationVector_z = gameRotationVector_z;
	}

	public float getStepCounter() {
		return stepCounter;
	}

	public void setStepCounter(float stepCounter) {
		this.stepCounter = stepCounter;
	}

	public float getStepDetector() {
		return stepDetector;
	}

	public void setStepDetector(float stepDetector) {
		this.stepDetector = stepDetector;
	}

	public int getSignificantMotion() {
		return significantMotion;
	}

	public void setSignificantMotion(int significantMotion) {
		this.significantMotion = significantMotion;
	}

}