## ActionList

#### <a name="org.jspresso.framework.view.action.ActionList"></a>ActionList

+ **Full name** : [`org.jspresso.framework.view.action.ActionList`](http://www.jspresso.org/external/maven-site/apidocs/org/jspresso/framework/view/action/ActionList.html)
+ **Super-type** : [`DefaultIconDescriptor`](#org.jspresso.framework.util.descriptor.DefaultIconDescriptor)



An action list is collection of actions tha can be described with a name, a
 description and an icon. Whether these information are visually leveraged
 depends on the place where the action list is used. For instance, an action
 list used to create a menu in a menu bar will be able to leverage its name and
 icon for menu representation. If it is used to define a toolbar part, none of
 them will be leveraged. The name of the action list is also used to identify
 the sibling action lists to be merged when inheriting action map together.



<table>
<caption>ActionList properties</caption>
<colgroup>
<col width="33%" />
<col width="66%" />
</colgroup>
<thead>
<tr class="header">
<th align="left">Property</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td align="left"><p><strong>actions</strong></p><p><code>List&#x200B;&lt;&#x200B;<a href="http://www.jspresso.org/external/maven-site/apidocs/org/jspresso/framework/view/action/IDisplayableAction.html">IDisplayable&#x200B;Action</a>&#x200B;&gt;&#x200B;</code></p></td>
<td><p>Assigns the list of actions owned by this action list.</p></td>
</tr>
<tr class="even">
<td align="left"><p><strong>collapsable</strong></p><p><code>boolean</code></p></td>
<td><p>Configures the action list so that it can be collapsed by view factories.
 Collapsable action lists can typically be rendered as combo buttons in UI
 channels that support it.
 <p>
 Default value is <code>false</code>.</p></td>
</tr>
<tr class="odd">
<td align="left"><p><strong>grantedRoles</strong></p><p><code>Collection&#x200B;&lt;&#x200B;String&#x200B;&gt;&#x200B;</code></p></td>
<td><p>Assigns the roles that are authorized to use this action list. It supports
 &quot;<b>!</b>&quot; prefix to negate the role(s). Whenever the user is not
 granted sufficient privileges, the action list is simply not displayed at
 runtime. Setting the collection of granted roles to <code>null</code>
 (default value) disables role based authorization, then access is granted
 to anyone.</p></td>
</tr>
<tr class="even">
<td align="left"><p><strong>permId</strong></p><p><code>String</code></p></td>
<td><p>Sets the permanent identifier to this application element. Permanent
 identifiers are used by different framework parts, like dynamic security or
 record/replay controllers to uniquely identify an application element.
 Permanent identifiers are generated by the SJS build based on the element
 id but must be explicitly set if Spring XML is used.</p></td>
</tr>
<tr class="odd">
<td align="left"><p><strong>renderingOptions</strong></p><p><code><a href="http://www.jspresso.org/external/maven-site/apidocs/org/jspresso/framework/util/gui/ERenderingOptions.html">ERendering&#x200B;Options</a></code></p></td>
<td><p>Indicates how the actions should be rendered. This is either a value of the
 <code>ERenderingOptions</code> enum or its equivalent string representation
 :
 <ul>
 <li><code>LABEL_ICON</code> for label and icon</li>
 <li><code>LABEL</code> for label only</li>
 <li><code>ICON</code> for icon only.</li>
 </ul>
 <p>
 Default value is <code>null</code>, i.e. determined from outside, e.g. the
 view factory or the owning action map.</p></td>
</tr>
</tbody>
</table>

---

