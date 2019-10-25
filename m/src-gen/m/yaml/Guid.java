/**
 * generated by Xtext
 */
package m.yaml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Guid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link m.yaml.Guid#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see m.yaml.YamlPackage#getGuid()
 * @model
 * @generated
 */
public interface Guid extends Element
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see m.yaml.YamlPackage#getGuid_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link m.yaml.Guid#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

} // Guid
