CollapseView (expand list)
==========================
Which allows you to hide/show details 
test clean_up

Setup
-----
* Copy class to your project

XML Usage
-----
you can define view in your xml layouts like this:
```xml
<com.example.test.CollapseView
        android:id="@+id/expandView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
        </com.example.test.CollapseView>
```

Simple Example
--------------
```java
public class SlidingExample extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create view for header
		LayoutInflater li = getLayoutInflater();
        View header = li.inflate(R.layout.exp_header, null);

        CollapseView collapseView = (CollapseView) findViewById(R.id.expandView);
        collapseView.setHeader(header);
        collapseView.setBackgroundHeaderColRes(R.drawable.options_block);
        collapseView.setBackgroundHeaderExpRes(R.drawable.options_block_active);
        collapseView.setBackgroundItemsRes(R.drawable.options_block_active_bottom_shadow);
        collapseView.setCollapsed(true);
		
		// Create views with content and add their to collapseView. Which can collapse.
        for (int i = 0; i < 10; i++) {
            View view = li.inflate(R.layout.exp_item1, null);
            collapseView.addContentView(view);
        }
	}
    
}
```

Methods
-------
* `setHeader(View view)` - Set view to header. Which will be a button and always show. 
* `setContentViews(List<View> list)` - Set views to main content. Who can collapse.
* `setContentView(View view)` - Set view to main content. Who can collapse.
* `addContentView(View view)` - Add view to main content. Who can collapse.
* `setBackgroundHeaderColDraw(Drawable background)` - Set background for header. When content is collapse. @param background The Drawable to use as the background, or null to remove the background.
* `setBackgroundHeaderColColor(int color)` - Sets the background color for header. When content is collapse. @param color the color of the background.
* `setBackgroundHeaderColRes(int resid)` - Set the background header when items hided to a given resource. The resource should refer to a Drawable object or 0 to remove the background. When content is collapse. @param resid The identifier of the resource.
* `setBackgroundHeaderExpDraw(Drawable background)` - Set background for header. When content is expand. @param background The Drawable to use as the background, or null to remove the background.
* `setBackgroundHeaderExpColor(int color)` - Sets the background color for header. When content is expand. @param color the color of the background.
* `setBackgroundHeaderExpRes(int resid)` - Set the background header when items hided to a given resource. The resource should refer to a Drawable object or 0 to remove the background. When content is expand. @param resid The identifier of the resource.
* `setBackgroundItemsDraw(Drawable background)` - Set background for items(collapsing). @param background The Drawable to use as the background, or null to remove the background.
* `setBackgroundItemsColor(int color)` - Sets the background color for items(collapsing). @param color the color of the background.
* `setBackgroundItemsRes(int resid)` - Set the background to a given resource. The resource should refer to a Drawable object or 0 to remove the background. @param resid The identifier of the resource.
* `setDivider(boolean exist)` - Set dividers between items. @param exist true to make dividers, false otherwise
* `setDividerColor(int color)` - Sets color for dividers. @param color the color of dividers.
* `setCollapsed(boolean collapse)` - Set items collapse. @param collapse true collapse, false expand.

Developed By
------------
* Andrew Comeloff
