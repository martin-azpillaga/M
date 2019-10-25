/**
 * generated by Xtext
 */
package m.cs;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link m.cs.Indexing#getIndex <em>Index</em>}</li>
 * </ul>
 *
 * @see m.cs.CsPackage#getIndexing()
 * @model
 * @generated
 */
public interface Indexing extends Portion
{
  /**
   * Returns the value of the '<em><b>Index</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' containment reference.
   * @see #setIndex(Expression)
   * @see m.cs.CsPackage#getIndexing_Index()
   * @model containment="true"
   * @generated
   */
  Expression getIndex();

  /**
   * Sets the value of the '{@link m.cs.Indexing#getIndex <em>Index</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' containment reference.
   * @see #getIndex()
   * @generated
   */
  void setIndex(Expression value);

} // Indexing
