package javacommon.util;

/**
 * <p>
 * Title:取随机数
 * </p>
 * <p>
 * Description:  
 * </p>
 * @author qiongguo
 * @version 1.0
 */

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomNum {

  private static Long randomnum = null;
  private static Float randomfloat = null;
  private static boolean floatvalue = false;
  private static long upper = 9999;
  private static long lower = 0;
  private static String algorithm = null;
  private static String provider = null;
  private static boolean secure = false;
  private static Random random = null;
  private static SecureRandom secrandom = null;

  private static float getFloat() {
    try{
      if (random == null && secrandom == null)
        generateRandomObject();
    }catch(Exception e){return -1;};
    if (random == null)
      return secrandom.nextFloat();
    else
      return random.nextFloat();
  }

  public static void generateRandomObject() throws Exception {

    //check to see if the object is a SecureRandom object
    if (secure) {
      try {
        // get an instance of a SecureRandom object
        if (provider != null)
          // search for algorithm in package provider
          secrandom = SecureRandom.getInstance(algorithm, provider);
        else
          secrandom = SecureRandom.getInstance(algorithm);
      } catch (NoSuchAlgorithmException ne) {
        throw new Exception(ne.getMessage());
      } catch (NoSuchProviderException pe) {
        throw new Exception(pe.getMessage());
      }
    } else
      random = new Random();
  }

  /**
   * generate the random number
   *
   */
  private static void generaterandom() {

    int tmprandom = 0; //��ʱ������������
    Integer rand;

    //check to see if float value is expected
    if (floatvalue)
      randomfloat = new Float(getFloat());
    else
      randomnum = new Long(lower + (long) ((getFloat() * (upper - lower))));
  }

  public static Number getRandom() {
    generaterandom();
    if (floatvalue)
      return randomfloat;
    else
      return randomnum;
  }

  public static void setRange(long low, long up) {

    //set the upper and lower bound of the range
    lower = low;
    upper = up;

    //check to see if a float value is expected
    if ((lower == 0) && (upper == 1))
      floatvalue = true;
  }

  /**
   * set the algorithm name
   *
   * @param value name of the algorithm to use for a SecureRandom object
   *
   */
  public final void setAlgorithm(String value) {
    algorithm = value;
    secure = true; // a SecureRandom object is to be used
  }

  public final void setProvider(String value)
  {
    provider = value;
  }

  public static void setRange(String value) {
    try
    {
      upper = new Long(value.substring(value.indexOf('-') + 1)).longValue();
    } catch (Exception ex) {
      upper = 9999;
    }

    try
    {
      lower = new Long(value.substring(0, value.indexOf('-'))).longValue();
    } catch (Exception ex) {
      lower = 0;
    }

    if ((lower == 0) && (upper == 1))
      floatvalue = true;

    if (upper < lower){
      long tmp = lower;
      lower = upper;
      upper = tmp;
    }
  }
}

