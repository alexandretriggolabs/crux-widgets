/*
 * Copyright 2011 cruxframework.org.
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
package org.cruxframework.crux.widgets.client.rss.feed;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class Feed extends JavaScriptObject
{
	protected Feed() {}
	
	public final native String getFeedUrl()/*-{
		return this.feedUrl;
	}-*/;	

	public final native String getTitle()/*-{
		return this.title;
	}-*/;	

	public final native String getLink()/*-{
		return this.link;
	}-*/;	
	
	public final native String getDescription()/*-{
		return this.description;
	}-*/;	
	
	public final native String getAuthor()/*-{
		return this.author;
	}-*/;	

	public final native JsArray<Entry> getEntries()/*-{
		return this.entries;
	}-*/;	
}
