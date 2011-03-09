/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.sempere.commons.constant;

/**
 * Constants class for HTTP headers
 *
 * @author bsempere
 */
public final class HeaderConstants {

    public static final String USER_AGENT = "user-agent";

    // Accept Content-Types that are acceptable Accept: text/plain
    // Accept-Charset Character sets that are acceptable Accept-Charset: iso-8859-5
    // Accept-Encoding Acceptable encodings Accept-Encoding: compress, gzip
    // Accept-Language Acceptable languages for response Accept-Language: da
    // Accept-Ranges Allows the server to indicate its acceptance of range requests for a resource Accept-Ranges: bytes
    // Authorization Authentication credentials for HTTP authentication Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
    // Cache-Control Used to specify directives that MUST be obeyed by all caching mechanisms along the request/response chain Cache-Control: no-cache
    // Connection What type of connection the user-agent would prefer Connection: close
    // Cookie an HTTP cookie previously sent by the server with Set-Cookie (below) Cookie: $Version=1; UserId=JohnDoe
    // Content-Type The mime-type of the body of the request (used with POST and PUT requests) Content-Type: application/x-www-form-urlencoded
    // Date The date and time that the message was sent Date: Tue, 15 Nov 1994 08:12:31 GMT
    // Expect Indicates that particular server behaviors are required by the client Expect: 100-continue
    // Host The domain name of the server (for virtual hosting), mandatory since HTTP/1.1 Host: en.wikipedia.org
    // If-Match Only perform the action if the client supplied entity matches the same entity on the server. This is mainly for methods like PUT to only update a resource if it has
    // not been modified since the user last updated it. If-Match: "737060cd8c284d8af7ad3082f209582d"
    // If-Modified-Since Allows a 304 Not Modified to be returned if content is unchanged If-Modified-Since: Sat, 29 Oct 1994 19:43:31 GMT
    // If-None-Match Allows a 304 Not Modified to be returned if content is unchanged, see HTTP ETag If-None-Match: "737060cd8c284d8af7ad3082f209582d"
    // If-Range If the entity is unchanged, send me the part(s) that I am missing; otherwise, send me the entire new entity If-Range: "737060cd8c284d8af7ad3082f209582d"
    // If-Unmodified-Since Only send the response if the entity has not been modified since a specific time. If-Unmodified-Since: Sat, 29 Oct 1994 19:43:31 GMT
    // Max-Forwards Limit the number of times the message can be forwarded through proxies or gateways. Max-Forwards: 10
    // Pragma Implementation-specific headers that may have various effects anywhere along the request-response chain. Pragma: no-cache
    // Proxy-Authorization Authorization credentials for connecting to a proxy. Proxy-Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
    // Range Request only part of an entity. Range: bytes=500-999
    // Referer This is the address of the previous web page from which a link to the currently requested page was followed. Referer: http://en.wikipedia.org/wiki/Main_Page
    // TE The transfer encodings the user is willing to accept. TE: trailers, deflate;q=0.5
    // Upgrade Ask the server to upgrade to another protocol. Upgrade: HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11
    // User-Agent The user agent string of the user agent User-Agent: Mozilla/5.0 (Linux; X11)
    // Via Informs the server of proxies through which the request was sent. Via: 1.0 fred, 1.1 nowhere.com (Apache/1.1)
    // Warn A general warning about possible problems with the entity body. Warn: 199 Miscellaneous warning

    // *************************************************************************
    //
    // Constructors
    //
    // *************************************************************************

    private HeaderConstants() {
    }
}