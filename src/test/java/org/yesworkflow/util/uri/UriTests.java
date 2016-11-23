package org.yesworkflow.util.uri;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UriTests {

	private Uri u;
	
	public void testEquals_SameObject() {
		
		u = new Uri("");
		assertThat(u, is(u));

		u = new Uri("/");
		assertThat(u, is(u));

		u = new Uri("a");
		assertThat(u, is(u));

		u = new Uri("/a");
		assertThat(u, is(u));

		u = new Uri("/a/b");
		assertThat(u, is(u));

		u = new Uri("foo:/");
		assertThat(u, is(u));

		u = new Uri("foo:/a/");
		assertThat(u, is(u));

		u = new Uri("foo:/a/b");
		assertThat(u, is(u));
	}
//	
//	public void testEquals_WrongType() {
//		
//		u = new ConcreteUri("");
//		assertThat(u, (""));
//
//		u = new ConcreteUri("/");
//		assertThat(u.equals("/"));
//
//		u = new ConcreteUri("a");
//		assertThat(u.equals(new UriTemplate("a")));
//
//		u = new ConcreteUri("/a");
//		assertThat(u.equals(new UriTemplate("/a")));
//	}	
//	
//	public void testEquals_Null() {
//		
//		u = new ConcreteUri("");
//		assertFalse(u.equals(null));
//
//		u = new ConcreteUri("/");
//		assertFalse(u.equals(null));
//
//		u = new ConcreteUri("a");
//		assertFalse(u.equals(null));
//
//		u = new ConcreteUri("/a");
//		assertFalse(u.equals(null));
//	}
//
//	public void testEquals_SameExpression() {
//		
//		u = new ConcreteUri("");
//		assertTrue(u.equals(new ConcreteUri("")));
//
//		u = new ConcreteUri("/");
//		assertTrue(u.equals(new ConcreteUri("/")));
//
//		u = new ConcreteUri("a");
//		assertTrue(u.equals(new ConcreteUri("a")));
//
//		u = new ConcreteUri("/a");
//		assertTrue(u.equals(new ConcreteUri("/a")));
//
//		u = new ConcreteUri("/a/b");
//		assertTrue(u.equals(new ConcreteUri("/a/b")));
//
//		u = new ConcreteUri("foo:/");
//		assertTrue(u.equals(new ConcreteUri("foo:/")));
//
//		u = new ConcreteUri("foo:/a/");
//		assertTrue(u.equals(new ConcreteUri("foo:/a/")));
//
//		u = new ConcreteUri("foo:/a/b");
//		assertTrue(u.equals(new ConcreteUri("foo:/a/b")));
//	}
//
//	public void testCompareTo_SameObject() {
//		
//		u = new ConcreteUri("");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("/");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("a");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("/a");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("/a/b");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("foo:/");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("foo:/a/");
//		assertEquals(0, u.compareTo(u));
//
//		u = new ConcreteUri("foo:/a/b");
//		assertEquals(0, u.compareTo(u));
//	}
//
//	public void testCompareTo_Null() {
//		
//		Exception e;
//
//		u = new ConcreteUri("");
//		e = null;
//		try { u.compareTo(null); } catch (NullPointerException ex) { e = ex; }
//		assertNotNull(e);
//
//		u = new ConcreteUri("/");
//		e = null;
//		try { u.compareTo(null); } catch (NullPointerException ex) { e = ex; }
//		assertNotNull(e);
//		
//		u = new ConcreteUri("a");
//		e = null;
//		try { u.compareTo(null); } catch (NullPointerException ex) { e = ex; }
//		assertNotNull(e);
//
//		u = new ConcreteUri("file:/a");
//		e = null;
//		try { u.compareTo(null); } catch (NullPointerException ex) { e = ex; }
//		assertNotNull(e);
//	}
//	
//	public void testCompareTo_SameExpression() {
//		
//		u = new ConcreteUri("");
//		assertEquals(0, u.compareTo(new ConcreteUri("")));
//
//		u = new ConcreteUri("/");
//		assertEquals(0, u.compareTo(new ConcreteUri("/")));
//
//		u = new ConcreteUri("a");
//		assertEquals(0, u.compareTo(new ConcreteUri("a")));
//
//		u = new ConcreteUri("/a");
//		assertEquals(0, u.compareTo(new ConcreteUri("/a")));
//
//		u = new ConcreteUri("/a/b");
//		assertEquals(0, u.compareTo(new ConcreteUri("/a/b")));
//
//		u = new ConcreteUri("foo:/");
//		assertEquals(0, u.compareTo(new ConcreteUri("foo:/")));
//
//		u = new ConcreteUri("foo:/a/");
//		assertEquals(0, u.compareTo(new ConcreteUri("foo:/a/")));
//
//		u = new ConcreteUri("foo:/a/b");
//		assertEquals(0, u.compareTo(new ConcreteUri("foo:/a/b")));
//	}
//
//	public void testCompareTo_DifferentExpressions() {
//		
//		u = new ConcreteUri("a");
//		assertTrue(u.compareTo(new ConcreteUri("b")) < 0);
//
//		u = new ConcreteUri("file:/a");
//		assertTrue(u.compareTo(new ConcreteUri("file:/b")) < 0);
//
//		u = new ConcreteUri("file:/b");
//		assertTrue(u.compareTo(new ConcreteUri("foo:/a")) < 0);
//
//		u = new ConcreteUri("/a");
//		assertTrue(u.compareTo(new ConcreteUri("/a/")) < 0);
//
//		u = new ConcreteUri("/");
//		assertTrue(u.compareTo(new ConcreteUri("/a")) < 0);
//	}
}
	

