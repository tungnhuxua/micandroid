package ningbq;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class FirstTest extends AndroidTestCase {
	
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAdd(){
		int i = 5 + 1 ;
		Assert.assertEquals(6, i); 
	}

}
