package org.yesworkflow.util.uri;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UriBaseTests {

	private Uri u;

	@Test public void testTrimTerminalSlash_HasNoSchemeNoSlash() {
		assertThat(UriBase.trimTerminalSlash("/"), is("/"));
		assertThat(UriBase.trimTerminalSlash("/a"), is("/a"));
		assertThat(UriBase.trimTerminalSlash("/ab"), is("/ab"));
		assertThat(UriBase.trimTerminalSlash("/foo"), is("/foo"));
		assertThat(UriBase.trimTerminalSlash("/foo/bar"), is("/foo/bar"));
	}
	
	@Test public void testTrimTerminalSlash_HasSlashNoScheme() {
		assertThat(UriBase.trimTerminalSlash("/a/"), is("/a"));
		assertThat(UriBase.trimTerminalSlash("/ab/"), is("/ab"));
		assertThat(UriBase.trimTerminalSlash("/foo/"), is("/foo"));
		assertThat(UriBase.trimTerminalSlash("/foo/bar/"), is("/foo/bar"));
	}

	@Test public void testTrimTerminalSlash_HasSchemeNoSlash() {
		assertThat(UriBase.trimTerminalSlash("foo:/"), is("foo:/"));
		assertThat(UriBase.trimTerminalSlash("foo:/a"), is("foo:/a"));
		assertThat(UriBase.trimTerminalSlash("foo:/ab"), is("foo:/ab"));
		assertThat(UriBase.trimTerminalSlash("file:/foo"), is("file:/foo"));
		assertThat(UriBase.trimTerminalSlash("data:/foo/bar"), is("data:/foo/bar"));
	}

	@Test public void testTrimTerminalSlash_HasSlashAndScheme() {
		assertThat(Uri.trimTerminalSlash("foo:/a/"), is("foo:/a"));
		assertThat(Uri.trimTerminalSlash("foo:/ab/"), is("foo:/ab"));
		assertThat(Uri.trimTerminalSlash("file:/foo/"), is("file:/foo"));
		assertThat(Uri.trimTerminalSlash("data:/foo/bar/"), is("data:/foo/bar"));
	}

//	@Test public void testExtractSchemeAndPath_HasScheme() {
//		
//		Uri.SchemePathPair spp;
//		
//		spp = Uri.extractSchemeAndPath("file:/");
//		assertThat("file", spp.scheme);
//		assertThat("/", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("file:/foo");
//		assertThat("file", spp.scheme);
//		assertThat("/foo", spp.path);
//
//		spp = Uri.extractSchemeAndPath("data:/foo/bar");
//		assertThat("data", spp.scheme);
//		assertThat("/foo/bar", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("foo:/1/2/bar");
//		assertThat("foo", spp.scheme);
//		assertThat("/1/2/bar", spp.path);
//	}
//
//	@Test public void testExtractSchemeAndPath_HasSchemeAndDoubleLeadingSlashes() {
//		
//		Uri.SchemePathPair spp;
//		
//		spp = Uri.extractSchemeAndPath("file://");
//		assertThat("file", spp.scheme);
//		assertThat("/", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("file://foo");
//		assertThat("file", spp.scheme);
//		assertThat("/foo", spp.path);
//
//		spp = Uri.extractSchemeAndPath("data://foo/bar");
//		assertThat("data", spp.scheme);
//		assertThat("/foo/bar", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("foo://1/2/bar");
//		assertThat("foo", spp.scheme);
//		assertThat("/1/2/bar", spp.path);
//	}
//
//	@Test public void testExtractSchemeAndPath_HasSchemeAndDoubleAndIntermediateLeadingSlashes() {
//		
//		Uri.SchemePathPair spp;
//		
//		spp = Uri.extractSchemeAndPath("file://");
//		assertThat("file", spp.scheme);
//		assertThat("/", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("file://foo");
//		assertThat("file", spp.scheme);
//		assertThat("/foo", spp.path);
//
//		spp = Uri.extractSchemeAndPath("data://foo//bar");
//		assertThat("data", spp.scheme);
//		assertThat("/foo/bar", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("foo://1//2//bar");
//		assertThat("foo", spp.scheme);
//		assertThat("/1/2/bar", spp.path);
//	}
//
//	
//	@Test public void testExtractSchemeAndPath_HasNoScheme() {
//		
//		Uri.SchemePathPair spp;
//		
//		spp = Uri.extractSchemeAndPath("/");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("/foo");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/foo", spp.path);
//
//		spp = Uri.extractSchemeAndPath("/foo/bar");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/foo/bar", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("/1/2/bar");
//		assertSame(Uri.NO_SCHEME,spp.scheme);
//		assertThat("/1/2/bar", spp.path);
//	}
//
//	@Test public void testExtractSchemeAndPath_HasNoSchemeButDoubleLeadingSlashes() {
//		
//		Uri.SchemePathPair spp;
//		
//		spp = Uri.extractSchemeAndPath("//");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("//foo");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/foo", spp.path);
//
//		spp = Uri.extractSchemeAndPath("//foo/bar");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/foo/bar", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("//1/2/bar");
//		assertSame(Uri.NO_SCHEME,spp.scheme);
//		assertThat("/1/2/bar", spp.path);
//	}
//
//	@Test public void testExtractSchemeAndPath_HasNoSchemeButLeadingAndIntermediateDoubleSlashes() {
//		
//		Uri.SchemePathPair spp;
//		
//		spp = Uri.extractSchemeAndPath("//foo//bar");
//		assertSame(Uri.NO_SCHEME, spp.scheme);
//		assertThat("/foo/bar", spp.path);
//		
//		spp = Uri.extractSchemeAndPath("//1//2//bar");
//		assertSame(Uri.NO_SCHEME,spp.scheme);
//		assertThat("/1/2/bar", spp.path);
//	}
//
//	
//	@Test public void testGetScheme_HasScheme() {
//		
//		u = new Uri("file:/foo", false);
//		assertThat("file", u.getScheme());
//
//		u = new Uri("data:/foo/bar", false);
//		assertThat("data", u.getScheme());
//
//		u = new Uri("foo:/1/2/bar", false);
//		assertThat("foo", u.getScheme());
//
//		u = new Uri("file:/foo", true);
//		assertThat("file", u.getScheme());
//
//		u = new Uri("data:/foo/bar", true);
//		assertThat("data", u.getScheme());
//
//		u = new Uri("foo:/1/2/bar", true);
//		assertThat("foo", u.getScheme());
//
//		u = new Uri("file:/foo");
//		assertThat("file", u.getScheme());
//
//		u = new Uri("data:/foo/bar");
//		assertThat("data", u.getScheme());
//
//		u = new Uri("foo:/1/2/bar");
//		assertThat("foo", u.getScheme());
//	}
//
//	@Test public void testGetScheme_HasNoScheme() {
//		
//		u = new Uri("/bar", false);
//		assertSame(Uri.NO_SCHEME, u.getScheme());
//		assertThat("", u.getScheme());
//
//		u = new Uri("/", false);
//		assertSame(Uri.NO_SCHEME, u.getScheme());
//		assertThat("", u.getScheme());
//		
//		u = new Uri("/bar", true);
//		assertSame(Uri.NO_SCHEME, u.getScheme());
//		assertThat("", u.getScheme());
//
//		u = new Uri("/", true);
//		assertSame(Uri.NO_SCHEME, u.getScheme());
//		assertThat("", u.getScheme());
//
//		u = new Uri("/bar");
//		assertSame(Uri.NO_SCHEME, u.getScheme());
//		assertThat("", u.getScheme());
//
//		u = new Uri("/");
//		assertSame(Uri.NO_SCHEME, u.getScheme());
//		assertThat("", u.getScheme());
//	}
//
//	@Test public void testGetPath_HasScheme() {
//		
//		u = new Uri("file:/foo", false);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data:/foo/bar", false);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo:/1/2/bar", false);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("file:/foo", true);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data:/foo/bar", true);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo:/1/2/bar", true);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("file:/foo");
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data:/foo/bar");
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo:/1/2/bar");
//		assertThat("/1/2/bar", u.getPath());
//	}
//
//	@Test public void testGetPath_HasSchemeAndDoubleLeadingSlashes() {
//		
//		u = new Uri("file://foo", false);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data://foo/bar", false);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo://1/2/bar", false);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("file://foo", true);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data://foo/bar", true);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo://1/2/bar", true);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("file://foo");
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data://foo/bar");
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo://1/2/bar");
//		assertThat("/1/2/bar", u.getPath());
//	}
//	
//	@Test public void testGetPath_HasNoScheme() {
//		
//		u = new Uri("/foo", true);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("/foo/bar", true);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("/1/2/bar", true);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("/foo", false);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("/foo/bar", false);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("/1/2/bar", false);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("/foo");
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("/foo/bar");
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("/1/2/bar");
//		assertThat("/1/2/bar", u.getPath());
//	}
//
//	@Test public void testGetPath_HasNoSchemeButDoubleLeadingSlashes() {
//		
//		u = new Uri("//foo", true);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("//foo/bar", true);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("//1/2/bar", true);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("//foo", false);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("//foo/bar", false);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("//1/2/bar", false);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("//foo");
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("//foo/bar");
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("//1/2/bar");
//		assertThat("/1/2/bar", u.getPath());
//}
//	
//	@Test public void testGetPath_HasTerminalSlash() {
//		
//		u = new Uri("file:/foo/", false);
//		assertThat("/foo/", u.getPath());
//
//		u = new Uri("data:/foo/bar/", false);
//		assertThat("/foo/bar/", u.getPath());
//
//		u = new Uri("foo:/1/2/bar/", false);
//		assertThat("/1/2/bar/", u.getPath());
//		
//		u = new Uri("/foo/", false);
//		assertThat("/foo/", u.getPath());
//
//		u = new Uri("/foo/bar/", false);
//		assertThat("/foo/bar/", u.getPath());
//
//		u = new Uri("/1/2/bar/", false);
//		assertThat("/1/2/bar/", u.getPath());
//
//		u = new Uri("file:/foo/", true);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("data:/foo/bar/", true);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("foo:/1/2/bar/", true);
//		assertThat("/1/2/bar", u.getPath());
//		
//		u = new Uri("/foo", true);
//		assertThat("/foo", u.getPath());
//
//		u = new Uri("/foo/bar", true);
//		assertThat("/foo/bar", u.getPath());
//
//		u = new Uri("/1/2/bar", true);
//		assertThat("/1/2/bar", u.getPath());
//
//		u = new Uri("file:/foo/");
//		assertThat("/foo/", u.getPath());
//
//		u = new Uri("data:/foo/bar/");
//		assertThat("/foo/bar/", u.getPath());
//
//		u = new Uri("foo:/1/2/bar/");
//		assertThat("/1/2/bar/", u.getPath());
//		
//		u = new Uri("/foo/");
//		assertThat("/foo/", u.getPath());
//
//		u = new Uri("/foo/bar/");
//		assertThat("/foo/bar/", u.getPath());
//
//		u = new Uri("/1/2/bar/");
//		assertThat("/1/2/bar/", u.getPath());
//		
//	}	
//	
//	@Test public void testGetExpression_HasNoEndSlash() {
//		
//		u = new Uri("foo:/bar", false);
//		assertThat("foo:/bar", u.getExpression());		
//
//		u = new Uri("foo:/1/bar", false);
//		assertThat("foo:/1/bar", u.getExpression());		
//		
//		u = new Uri("foo:/1/2/bar", false);
//		assertThat("foo:/1/2/bar", u.getExpression());		
//		
//		u = new Uri("foo:/bar", true);
//		assertThat("foo:/bar", u.getExpression());		
//
//		u = new Uri("foo:/1/bar", true);
//		assertThat("foo:/1/bar", u.getExpression());		
//		
//		u = new Uri("foo:/1/2/bar", true);
//		assertThat("foo:/1/2/bar", u.getExpression());		
//
//		u = new Uri("foo:/bar");
//		assertThat("foo:/bar", u.getExpression());		
//
//		u = new Uri("foo:/1/bar");
//		assertThat("foo:/1/bar", u.getExpression());		
//		
//		u = new Uri("foo:/1/2/bar");
//		assertThat("foo:/1/2/bar", u.getExpression());		
//	
//	}
//
//	
//	@Test public void testGetExpression_HasEndSlash() {
//		
//		u = new Uri("/", false);
//		assertThat("/", u.getExpression());		
//
//		u = new Uri("foo:/", false);
//		assertThat("foo:/", u.getExpression());		
//
//		u = new Uri("foo:/bar/", false);
//		assertThat("foo:/bar/", u.getExpression());		
//
//		u = new Uri("foo:/1/bar/", false);
//		assertThat("foo:/1/bar/", u.getExpression());		
//		
//		u = new Uri("foo:/1/2/bar/", false);
//		assertThat("foo:/1/2/bar/", u.getExpression());
//		
//		u = new Uri("foo:/1/2/v1/bar/", false);
//		assertThat("foo:/1/2/v1/bar/", u.getExpression());
//
//		u = new Uri("/", true);
//		assertThat("/", u.getExpression());		
//
//		u = new Uri("foo:/", true);
//		assertThat("foo:/", u.getExpression());		
//
//		u = new Uri("foo:/bar/", true);
//		assertThat("foo:/bar", u.getExpression());		
//
//		u = new Uri("foo:/1/bar/", true);
//		assertThat("foo:/1/bar", u.getExpression());		
//		
//		u = new Uri("foo:/1/2/bar/", true);
//		assertThat("foo:/1/2/bar", u.getExpression());
//		
//		u = new Uri("foo:/1/2/v1/bar/", true);
//		assertThat("foo:/1/2/v1/bar", u.getExpression());
//		
//		u = new Uri("/");
//		assertThat("/", u.getExpression());		
//
//		u = new Uri("foo:/");
//		assertThat("foo:/", u.getExpression());		
//
//		u = new Uri("foo:/bar/");
//		assertThat("foo:/bar/", u.getExpression());		
//
//		u = new Uri("foo:/1/bar/");
//		assertThat("foo:/1/bar/", u.getExpression());		
//		
//		u = new Uri("foo:/1/2/bar/");
//		assertThat("foo:/1/2/bar/", u.getExpression());
//		
//		u = new Uri("foo:/1/2/v1/bar/");
//		assertThat("foo:/1/2/v1/bar/", u.getExpression());
//	}
//
//	
//	@Test public void testExtractPathName() {
//		
//		assertThat("", Uri.extractPathName(""));
//		assertThat("", Uri.extractPathName("/"));
//
//		assertThat("", Uri.extractPathName("/bar/"));
//		assertThat("bar", Uri.extractPathName("/bar"));
//		assertThat("bar", Uri.extractPathName("bar"));
//		assertThat("", Uri.extractPathName("bar/"));
//
//		assertThat("bar", Uri.extractPathName("/1/2/v1/bar"));
//		assertThat("", Uri.extractPathName("/1/2/v1/bar/"));
//
//		assertThat("bar", Uri.extractPathName("bar"));
//		assertThat("v1bar", Uri.extractPathName("/1/2/v1bar"));
//		assertThat("", Uri.extractPathName("/1/2/v1bar/"));
//		assertThat("v1bar", Uri.extractPathName("/1/2/v1bar"));
//		assertThat("", Uri.extractPathName("/1/2/v1bar/"));
//	}
//	
//	@Test public void testGetName() {
//		
//		u = new Uri("", false);
//		assertThat("", u.getName());		
//
//		u = new Uri("/", false);
//		assertThat("", u.getName());
//
//		u = new Uri("/bar/", false);
//		assertThat("", u.getName());
//
//		u = new Uri("/bar", false);
//		assertThat("bar", u.getName());
//
//		u = new Uri("bar", false);
//		assertThat("bar", u.getName());
//
//		u = new Uri("bar/", false);
//		assertThat("", u.getName());
//
//		u = new Uri("foo:/bar", false);
//		assertThat("bar", u.getName());		
//
//		u = new Uri("foo:/1/bar", false);
//		assertThat("bar", u.getName());		
//		
//		u = new Uri("foo:/1/2/bar", false);
//		assertThat("bar", u.getName());		
//				
//		u = new Uri("/1/bar", false);
//		assertThat("bar", u.getName());		
//		
//		u = new Uri("/1/2/bar", false);
//		assertThat("bar", u.getName());		
//				
//		u = new Uri("foo:/", false);
//		assertThat("", u.getName());		
//
//		u = new Uri("foo:/1/", false);
//		assertThat("", u.getName());
//		
//		u = new Uri("foo:/1/2/", false);
//		assertThat("", u.getName());		
//
//		u = new Uri("foo:/1/2/", false);
//		assertThat("", u.getName());
//
//		u = new Uri("/1/", false);
//		assertThat("", u.getName());		
//		
//		u = new Uri("/1/2/", false);
//		assertThat("", u.getName());		
//		
//		u = new Uri("/1/2/", false);
//		assertThat("", u.getName());
//
//		u = new Uri("", true);
//		assertThat("", u.getName());		
//
//		u = new Uri("/", true);
//		assertThat("", u.getName());
//
//		u = new Uri("/bar/", true);
//		assertThat("bar", u.getName());
//
//		u = new Uri("/bar", true);
//		assertThat("bar", u.getName());
//
//		u = new Uri("bar", true);
//		assertThat("bar", u.getName());
//
//		u = new Uri("bar/", true);
//		assertThat("bar", u.getName());
//
//		u = new Uri("foo:/bar", true);
//		assertThat("bar", u.getName());		
//
//		u = new Uri("foo:/1/bar", true);
//		assertThat("bar", u.getName());		
//		
//		u = new Uri("foo:/1/2/bar", true);
//		assertThat("bar", u.getName());		
//				
//		u = new Uri("/1/bar", true);
//		assertThat("bar", u.getName());		
//		
//		u = new Uri("/1/2/bar", true);
//		assertThat("bar", u.getName());		
//				
//		u = new Uri("foo:/", true);
//		assertThat("", u.getName());		
//
//		u = new Uri("foo:/1/", true);
//		assertThat("1", u.getName());
//		
//		u = new Uri("foo:/1/2/", true);
//		assertThat("2", u.getName());		
//
//		u = new Uri("foo:/1/2/", true);
//		assertThat("2", u.getName());
//
//		u = new Uri("/1/", true);
//		assertThat("1", u.getName());		
//		
//		u = new Uri("/1/2/", true);
//		assertThat("2", u.getName());		
//		
//		u = new Uri("/1/2/", true);
//		assertThat("2", u.getName());
//		
//		u = new Uri("");
//		assertThat("", u.getName());		
//
//		u = new Uri("/");
//		assertThat("", u.getName());
//
//		u = new Uri("/bar/");
//		assertThat("", u.getName());
//
//		u = new Uri("/bar");
//		assertThat("bar", u.getName());
//
//		u = new Uri("bar");
//		assertThat("bar", u.getName());
//
//		u = new Uri("bar/");
//		assertThat("", u.getName());
//
//		u = new Uri("foo:/bar");
//		assertThat("bar", u.getName());		
//
//		u = new Uri("foo:/1/bar");
//		assertThat("bar", u.getName());		
//		
//		u = new Uri("foo:/1/2/bar");
//		assertThat("bar", u.getName());		
//				
//		u = new Uri("/1/bar");
//		assertThat("bar", u.getName());		
//		
//		u = new Uri("/1/2/bar");
//		assertThat("bar", u.getName());		
//				
//		u = new Uri("foo:/");
//		assertThat("", u.getName());		
//
//		u = new Uri("foo:/1/");
//		assertThat("", u.getName());
//		
//		u = new Uri("foo:/1/2/");
//		assertThat("", u.getName());		
//
//		u = new Uri("foo:/1/2/");
//		assertThat("", u.getName());
//
//		u = new Uri("/1/");
//		assertThat("", u.getName());		
//		
//		u = new Uri("/1/2/");
//		assertThat("", u.getName());		
//		
//		u = new Uri("/1/2/");
//		assertThat("", u.getName());
//	}
//	
//	@Test public void testExtractParent() {
//		
//		assertThat("", Uri.extractParent(""));
//		assertThat("", Uri.extractParent("/"));
//
//		assertThat("", Uri.extractParent("/bar/"));
//		assertThat("/", Uri.extractParent("/bar"));
//		assertThat("", Uri.extractParent("bar"));
//		assertThat("", Uri.extractParent("bar/"));
//
//		assertThat("/1/2/v1/", Uri.extractParent("/1/2/v1/bar"));
//		assertThat("", Uri.extractParent("/1/2/v1/bar/"));
//
//		assertThat("", Uri.extractParent("bar"));
//		assertThat("/1/2/", Uri.extractParent("/1/2/v1bar"));
//		assertThat("", Uri.extractParent("/1/2/v1bar/"));
//		assertThat("/1/2/", Uri.extractParent("/1/2/v1bar"));
//		assertThat("", Uri.extractParent("/1/2/v1bar/"));
//		
//		assertThat("foo:", Uri.extractParent("foo:bar"));
//		assertThat("foo:/1/2/", Uri.extractParent("foo:/1/2/v1bar"));
//		assertThat("", Uri.extractParent("foo:/1/2/v1bar/"));
//		assertThat("foo:/1/2/", Uri.extractParent("foo:/1/2/v1bar"));
//		assertThat("", Uri.extractParent("foo:/1/2/v1bar/"));
//	}
	
	@Test public void testEncodeString() {
		assertThat(UriBase.encodeString("abcd_1234_ABC-123~aBcD.5zZ"), 
				 					is("abcd_1234_ABC-123~aBcD.5zZ"));
		assertThat(UriBase.encodeString("abc def  123   456    _~.-"), 
									is("abc%20def%20%20123%20%20%20456%20%20%20%20_~.-"));
		assertThat(UriBase.encodeString("a$a"), 
									is("a%24a"));
		assertThat(UriBase.encodeString("a\ta"), 
									is("a%09a"));
	}

    @Test public void testEnsureTerminalSlash_inputEndsWithSingleSlash() throws Exception {
        assertThat(UriBase.ensureTerminalSlash("http:/foo/"), is("http:/foo/"));
    }

    @Test public void testEnsureTerminalSlash_inputEndsWithNonSlash() throws Exception {
        assertThat(UriBase.ensureTerminalSlash("http:/foo"), is("http:/foo/"));
    }

    @Test public void testEnsureTerminalSlash_inputEndsWithDoubleSlash() throws Exception {
        assertThat(UriBase.ensureTerminalSlash("http:/foo//"), is("http:/foo//"));
    }

    @Test public void testEnsureTerminalSlash_inputIsSlash() throws Exception {
        assertThat(UriBase.ensureTerminalSlash("/"), is("/"));
    }

    @Test public void testEnsureTerminalSlash_inputIsEmptyString() throws Exception {
        assertThat(UriBase.ensureTerminalSlash(""), is("/"));
    }

    @Test public void testEnsureTerminalSlash_inputIsNull() throws Exception {
        assertThat(UriBase.ensureTerminalSlash(null), is("/"));
    }
}