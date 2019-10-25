/**
 * generated by Xtext
 */
package m.cs;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link m.cs.Type#isSealed <em>Sealed</em>}</li>
 *   <li>{@link m.cs.Type#getType <em>Type</em>}</li>
 *   <li>{@link m.cs.Type#getName <em>Name</em>}</li>
 *   <li>{@link m.cs.Type#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link m.cs.Type#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @see m.cs.CsPackage#getType()
 * @model
 * @generated
 */
public interface Type extends EObject
{
  /**
   * Returns the value of the '<em><b>Sealed</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sealed</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sealed</em>' attribute.
   * @see #setSealed(boolean)
   * @see m.cs.CsPackage#getType_Sealed()
   * @model
   * @generated
   */
  boolean isSealed();

  /**
   * Sets the value of the '{@link m.cs.Type#isSealed <em>Sealed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sealed</em>' attribute.
   * @see #isSealed()
   * @generated
   */
  void setSealed(boolean value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link m.cs.TypeEnum}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see m.cs.TypeEnum
   * @see #setType(TypeEnum)
   * @see m.cs.CsPackage#getType_Type()
   * @model
   * @generated
   */
  TypeEnum getType();

  /**
   * Sets the value of the '{@link m.cs.Type#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see m.cs.TypeEnum
   * @see #getType()
   * @generated
   */
  void setType(TypeEnum value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(TypeName)
   * @see m.cs.CsPackage#getType_Name()
   * @model containment="true"
   * @generated
   */
  TypeName getName();

  /**
   * Sets the value of the '{@link m.cs.Type#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(TypeName value);

  /**
   * Returns the value of the '<em><b>Super Types</b></em>' containment reference list.
   * The list contents are of type {@link m.cs.TypeName}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Types</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Types</em>' containment reference list.
   * @see m.cs.CsPackage#getType_SuperTypes()
   * @model containment="true"
   * @generated
   */
  EList<TypeName> getSuperTypes();

  /**
   * Returns the value of the '<em><b>Members</b></em>' containment reference list.
   * The list contents are of type {@link m.cs.Member}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Members</em>' containment reference list.
   * @see m.cs.CsPackage#getType_Members()
   * @model containment="true"
   * @generated
   */
  EList<Member> getMembers();

} // Type
