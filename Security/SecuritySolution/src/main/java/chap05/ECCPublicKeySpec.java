package chap05;



import java.math.BigInteger;

import java.security.spec.KeySpec;


/**
 * Class ECCPublicKeySpec
 *
 *
 * Copyright:    Copyright (c) 2002 Wiley Publishing, Inc.
 * @author Rich Helton <rhelton@richware.com>
 * @version 1.0  01-FEB-2002
* DISCALIMER: Limit of Liability/Disclaimer of Warranty: 
* This code is used for educational purposes only. This software is provided "As Is" and 
* there are no implied warranties. While the publisher and author
* have used their best efforts in preparing this book and software, they make no
* representations or warranties and specifically disclaim any implied
* warranties of merchantability or fitness for a particular purpose. No
* warranty may be created or extended by sales representatives or written
* sales materials. The advice and strategies contained herein may not be
* suitable for your situation. You should consult with a professional where
* appropriate. Neither the publisher nor author shall be liable for any loss
* of profit or any other commercial damages, including but not limited to
* special, incidental, consequential, or other damages, including, but not limited to,
* procurement of substitute goods or services, loss of use, data, profits, or business
* interruption by cause of use or theory of use arising in any damage.
 */
public class ECCPublicKeySpec implements KeySpec
 {

  private BigInteger qx_;
  private BigInteger qy_;

  /**
   * Constructor ECCPublicKeySpec
   *
   *
   * @param qy
   * @param qx
   *
   */
  public ECCPublicKeySpec(BigInteger qy, BigInteger qx)
   {
    qx_ = qx;
    qy_ = qy;
  }

  /**
   * Method getQY
   *
   *
   * @return
   *
   */
  public BigInteger getQY()
   {
    return qy_;
  }

  /**
   * Method getQX
   *
   *
   * @return
   *
   */
  public BigInteger getQX()
   {
    return qx_;
  }
}


/*--- Formatted in Sun Java Convention Style on Tue, Mar 5, '02 ---*/


/*------ Formatted by Jindent 3.24 Basic 1.0 --- http://www.jindent.de ------*/
