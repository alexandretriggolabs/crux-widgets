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
package org.cruxframework.crux.widgets.rebind.swapcontainer;

import org.cruxframework.crux.core.client.utils.EscapeUtils;
import org.cruxframework.crux.core.client.utils.StringUtils;
import org.cruxframework.crux.core.rebind.AbstractProxyCreator.SourcePrinter;
import org.cruxframework.crux.core.rebind.CruxGeneratorException;
import org.cruxframework.crux.core.rebind.screen.widget.WidgetCreator;
import org.cruxframework.crux.core.rebind.screen.widget.WidgetCreatorContext;
import org.cruxframework.crux.core.rebind.screen.widget.creator.children.WidgetChildProcessor;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.DeclarativeFactory;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagAttributeDeclaration;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagAttributesDeclaration;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagChild;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagChildren;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagConstraints;
import org.cruxframework.crux.widgets.client.swapcontainer.SwapContainer;

class SwapContainerContext extends WidgetCreatorContext
{
	protected boolean hasActiveView = false;
}

/**
 * 
 * @author Thiago Bustamante
 *
 */
@DeclarativeFactory(id="swapViewContainer", library="widgets", targetWidget=SwapContainer.class)
@TagChildren({
	@TagChild(SwapContainerFactory.ViewProcessor.class)
})
public class SwapContainerFactory extends WidgetCreator<SwapContainerContext>
{
    @TagConstraints(minOccurs="0", maxOccurs="unbounded", tagName="view")
    @TagAttributesDeclaration({
    	@TagAttributeDeclaration(value="name", required=true),
    	@TagAttributeDeclaration(value="active", type=Boolean.class, defaultValue="false")
    })
    public static class ViewProcessor extends WidgetChildProcessor<SwapContainerContext> 
    {
    	@Override
    	public void processChildren(SourcePrinter out, SwapContainerContext context) throws CruxGeneratorException
    	{
    		String activeProperty = context.readChildProperty("active");
    		boolean active = false;
    		if (!StringUtils.isEmpty(activeProperty))
    		{
    			active = Boolean.parseBoolean(activeProperty);
    		}
    		if (active)
    		{
    			if (context.hasActiveView)
    			{
    				throw new CruxGeneratorException("SwapContainer ["+context.getWidgetId()+"], declared on view ["+getWidgetCreator().getView().getId()+"], declares more than one active View. Only one active view is allowed form the container.");
    			}
    			context.hasActiveView = true;
    		}
    		out.println(context.getWidget()+".loadView("+EscapeUtils.quote(context.readChildProperty("name"))+", "+active+");");
    	}
    }
    
	@Override
    public SwapContainerContext instantiateContext()
    {
	    return new SwapContainerContext();
    }
}
