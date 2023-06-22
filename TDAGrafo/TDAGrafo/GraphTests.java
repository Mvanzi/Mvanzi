package TDAGrafo;

// Librer�as JUNIT
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Tester desarrollado con JUnit 4 para la estructura Graph (no dirigido).
 * 
 * @author Gonzalo Ezequiel Arr� - Departamento de Ciencias e Ingenier�a de la
 *         Computaci�n - Universidad Nacional del Sur -
 *         gonzalo.arro@hotmail.com
 * 
 * @version 1.0 Agosto 2017
 *
 */

public class GraphTests {

	// OBJETOS DE PRUEBA

	private Graph<Object, Object> graph;
	private Object o1;

	// INICIALIZACI�N Y LIMPIEZA DE OBJETOS PARA LAS PRUEBAS

	/**
	 * Setup inicial de los objetos utilizados en los casos de prueba. (llamado
	 * antes de cada test)
	 */
	@Before
	public void setUp() {
		graph = new Grafo_matriz_adyacencia<Object, Object>(1000); // cambiar esta l�nea para probar
												// distintas implementaciones
		o1 = new Object();
	}

	/**
	 * Limpieza de los objetos utilizados en los casos de prueba. (llamado
	 * despu�s de cada test)
	 */
	@After
	public void tearDown() {
		graph = null; // esto es importante ya que los objetos pueden
		// persistir durante la ejecuci�n de todos los
		// tests, dejando objetos innecesarios en memoria
		// que ya no se est�n usando
		o1 = null;
	}

	// TESTS

	/**
	 * Comprueba que el m�todo vertices retorna una colecci�n iterable vac�a si
	 * no hay v�rtices en el grafo.
	 */
	@Test
	public void vertices_emptyGraph_returnsEmptyCollection() {
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertFalse("El m�todo vertices no retorna una colecci�n iterable vac�a para un grafo sin v�rtices.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que el m�todo edges retorna una colecci�n iterable vac�a si no
	 * hay arcos en el grafo.
	 */
	@Test
	public void edges_emptyGraph_returnsEmptyCollection() {
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse("El m�todo edges no retorna una colecci�n iterable vac�a para un grafo sin arcos.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el m�todo incidentEdges lanza InvalidVertexException con un
	 * v�rtice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void incidentEdges_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.incidentEdges(null);
	}

	/**
	 * Comprueba que el m�todo incidentEdges devuelve una colecci�n vac�a si se
	 * llama sobre un v�rtice sin arcos.
	 */
	@Test
	public void incidentEdges_vertexWithoutEdges_returnsEmptyCollection() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Iterable<Edge<Object>> incidentEdges = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIterator = incidentEdges.iterator();
		assertFalse("El m�todo incidentEdges no retorna una colecci�n iterable vac�a para un v�rtice sin arcos.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el m�todo opposite lanza InvalidVertexException con un
	 * v�rtice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void opposite_nullVertex_throwsIVE() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.opposite(null, e);
	}

	/**
	 * Comprueba que el m�todo opposite lanza InvalidEdgeException con un arco
	 * nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void opposite_nullEdge_throwsIEE() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.opposite(v1, null);
	}

	/**
	 * Comprueba que el m�todo opposite lanza InvalidEdgeException si el arco no
	 * incide en el v�rtice.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void opposite_edgeNotConnected_throwsIEE() throws InvalidEdgeException, InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		Vertex<Object> v3 = graph.insertVertex(new Object());
		graph.opposite(v3, e);
	}

	/**
	 * Comprueba que el m�todo opposite retorna el v�rtice opuesto de un v�rtice
	 * insertado como primer par�metro del arco (origen).
	 */
	@Test
	public void opposite_predecessorVertex_returnsOpposite() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		assertEquals("El m�todo opposite no retorna el opuesto de un v�rtice insertado como primer par�metro del arco.",
				v2, graph.opposite(v1, e));
	}

	/**
	 * Comprueba que el m�todo opposite retorna el v�rtice opuesto de un v�rtice
	 * insertado como segundo par�metro del arco (destino).
	 */
	@Test
	public void opposite_successorEdge_returnsOpposite() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		assertEquals("El m�todo opposite no retorna el opuesto de un v�rtice insertado como primer par�metro del arco.",
				v1, graph.opposite(v2, e));
	}

	/**
	 * Comprueba que el m�todo endvertices lanza InvalidEdgeException con un
	 * arco nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void endvertices_nullEdge_throwsIEE() throws InvalidEdgeException {
		graph.endvertices(null);
	}

	/**
	 * Comprueba que el m�todo endVertices retorna correctamente el arreglo de
	 * v�rtices para un arco.
	 */
	@Test
	public void endVertices_normalBehavior_returnsEndVertexs() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		Vertex<Object> endVertices[] = graph.endvertices(e);
		assertEquals("El m�todo endVertices retorna un arreglo que no tiene exactamente dos v�rtices.", 2,
				endVertices.length);
		assertEquals(
				"El primer v�rtice del arreglo retornado por endVertices no coincide con el primer v�rtice del arco.",
				v1, endVertices[0]);
		assertEquals(
				"El segundo v�rtice del arreglo retornado por endVertices no coincide con el segundo v�rtice del arco.",
				v2, endVertices[1]);
	}

	/**
	 * Comprueba que el m�todo areAdjacent lanza InvalidVertexException cuando
	 * el primer v�rtice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_firstVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.areAdjacent(null, v1);
	}

	/**
	 * Comprueba que el m�todo areAdjacent lanza InvalidVertexException cuando
	 * el segundo v�rtice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_secondVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.areAdjacent(v1, null);
	}

	/**
	 * Comprueba que el m�todo areAdjacent lanza InvalidVertexException cuando
	 * ambos v�rtices son nulos.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_bothVerticesNull_throwsIVE() throws InvalidVertexException {
		graph.areAdjacent(null, null);
	}

	/**
	 * Comprueba que el m�todo areAdjacent retorna true para dos v�rtices
	 * adyacentes con un �nico arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesPredecessorFirst_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El m�todo areAdjacent no retorna true para dos v�rtices con un arco entre ellos.",
				graph.areAdjacent(v1, v2));
	}
	
	/**
	 * Comprueba que el m�todo areAdjacent retorna true para dos v�rtices
	 * adyacentes con un �nico arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesSuccessorFirst_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El m�todo areAdjacent no retorna true para dos v�rtices con un arco entre ellos.",
				graph.areAdjacent(v2, v1));
	}

	/**
	 * Comprueba que el m�todo areAdjacent retorna true para dos v�rtices
	 * adyacentes con m�s de un arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesTwoEdges_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El m�todo areAdjacent no retorna true para dos v�rtices con un arco entre ellos.",
				graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el m�todo areAdjacent retorna false para dos v�rtices no
	 * adyacentes.
	 */
	@Test
	public void areAdjacent_notAdjacentsVertexs_returnsFalse() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		assertFalse("El m�todo areAdjacent retorna true para dos v�rtices no adyacentes.", graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el m�todo replace lanza InvalidVertexException con un
	 * v�rtice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void replace_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.replace(null, new Object());
	}

	/**
	 * Comprueba que el m�todo replace retorna correctamente el elemento previo
	 * en el v�rtice pasado por par�metro.
	 */
	@Test
	public void replace_validVertex_returnsOldElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		assertEquals("El m�todo replace no retorna el elemento previo en el v�rtice pasado por par�metro.", o1,
				graph.replace(v1, new Object()));

	}

	/**
	 * Comprueba que el m�todo replace setea correctamente el nuevo elemento al
	 * v�rtice pasado por par�metro.
	 */
	@Test
	public void replace_validVertex_setsNewElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.replace(v1, o1);
		assertEquals("El m�todo replace no setea correctamente el nuevo elemento.", o1, v1.element());
	}

	/**
	 * Comprueba que el m�todo insertVertex retorna correctamente el v�rtice
	 * insertado.
	 */
	@Test
	public void insertVertex_emptyGraph_returnsInsertedVertex() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		assertEquals("El m�todo insertVertex no retorna correctamente el v�rtice insertado.", o1, v1.element());
	}

	/**
	 * Comprueba que un v�rtice insertado aparece correctamente en la colecci�n
	 * de v�rtices del grafo.
	 */
	@Test
	public void insertVertex_emptyGraph_newVertexInGraph() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertTrue("Luego de insertar un v�rtice, se sigue retornando una colecci�n vac�a en el m�todo vertices.",
				verticesIterator.hasNext());
		assertEquals(
				"Luego de insertar un v�rtice, el elemento del mismo no coincide con aquel presente en la colecci�n retornada por el m�todo vertices.",
				v1, verticesIterator.next());
		assertFalse(
				"Luego de insertar un �nico v�rtice, el m�todo vertices retorna una colecci�n con m�s de un elemento.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que el m�todo insertEdge lanza InvalidVertexException cuando el
	 * primer v�rtice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_firstVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.insertEdge(null, v1, new Object());
	}

	/**
	 * Comprueba que el m�todo insertEdge lanza InvalidVertexException cuando el
	 * segundo v�rtice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_secondVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.insertEdge(v1, null, new Object());
	}

	/**
	 * Comprueba que el m�todo insertEdge lanza InvalidVertexException cuando
	 * ambos v�rtices son nulos.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_bothVerticesNull_throwsIVE() throws InvalidVertexException {
		graph.insertEdge(null, null, new Object());
	}

	/**
	 * Comprueba que el m�todo insertEdge retorna correctamente el arco
	 * insertado.
	 */
	@Test
	public void insertEdge_emptyGraph_returnsInsertedEdge() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		assertEquals("El m�todo insertEdge retorna un arco cuyo elemento no coincide con el pasado por par�metro.", o1,
				e.element());
	}

	/**
	 * Comprueba que un arco insertado aparece correctamente en la colecci�n de
	 * arcos del grafo.
	 */
	@Test
	public void insertEdge_emptyGraph_newEdgeInGraph() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertTrue("Luego de insertar un arco, se sigue retornando una colecci�n vac�a en el m�todo edges.",
				edgesIterator.hasNext());
		assertEquals(
				"Luego de insertar un arco, el elemento del mismo no coincide con aquel presente en la colecci�n retornada por el m�todo edges.",
				e, edgesIterator.next());
		assertFalse("Luego de insertar un �nico arco, el m�todo edges retorna una colecci�n con m�s de un elemento.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de a�adir un arco el mismo aparece como arco
	 * incidente en el v�rtice pasado como primer par�metro al arco.
	 */
	@Test
	public void insertEdge_emptyGraph_EdgeInV1AdjacencyList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> incidentEdgesV1 = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIteratorV1 = incidentEdgesV1.iterator();
		assertTrue(
				"Luego de insertar un arco a partir de un dado v�rtice, se sigue retornando una colecci�n vac�a en los arcos incidentes de dicho v�rtice.",
				edgesIteratorV1.hasNext());
		assertEquals(
				"Luego de insertar un arco a partir de un dado v�rtice, el elemento del arco insertado no coincide con aquel devuelto en la lista de arcos incidentes de dicho v�rtice.",
				e, edgesIteratorV1.next());
		assertFalse(
				"Luego de insertar un arco a partir de un dado v�rtice, se retorna una colecci�n con m�s de un elemento en los arcos incidentes de dicho v�rtice.",
				edgesIteratorV1.hasNext());
	}

	/**
	 * Comprueba que luego de a�adir un arco el mismo aparece como arco
	 * incidente en el v�rtice pasado como segundo par�metro al arco.
	 */
	@Test
	public void insertEdge_emptyGraph_EdgeInV2AdjacencyList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> incidentEdgesV2 = graph.incidentEdges(v2);
		Iterator<Edge<Object>> edgesIteratorV2 = incidentEdgesV2.iterator();
		assertTrue(
				"Luego de insertar un arco a partir de un dado v�rtice, se sigue retornando una colecci�n vac�a en los arcos incidentes de dicho v�rtice.",
				edgesIteratorV2.hasNext());
		assertEquals(
				"Luego de insertar un arco a partir de un dado v�rtice, el elemento del arco insertado no coincide con aquel devuelto en la lista de arcos incidentes de dicho v�rtice.",
				e, edgesIteratorV2.next());
		assertFalse(
				"Luego de insertar un arco a partir de un dado v�rtice, se retorna una colecci�n con m�s de un elemento en los arcos incidentes de dicho v�rtice.",
				edgesIteratorV2.hasNext());
	}

	/**
	 * Comprueba que el m�todo removeVertex lanza InvalidVertexException con un
	 * v�rtice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void removeVertex_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.removeVertex(null);
	}

	/**
	 * Comprueba que el m�todo removeVertex retorna correctamente el elemento
	 * del v�rtice eliminado.
	 */
	@Test
	public void removeVertex_validVertex_returnsElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		Object element = graph.removeVertex(v1);
		assertEquals("El m�todo removeVertex no retorna el elemento del v�rtice eliminado.", o1, element);
	}

	/**
	 * Comprueba que luego de eliminar un v�rtice mediante el m�todo
	 * removeVertex, el mismo ya no es devuelto en la lista de v�rtices del
	 * grafo.
	 */
	@Test
	public void removeVertex_validVertex_removedFromVerticesList() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		graph.removeVertex(v1);
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertFalse(
				"El m�todo vertices no retorna una colecci�n iterable vac�a luego de eliminar el �nico v�rtice del grafo.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de eliminar un v�rtice con un arco mediante el m�todo
	 * removeVertex, dicho arco ya no est� presente en la lista de arcos del
	 * grafo.
	 */
	@Test
	public void removeVertex_vertexWithEdge_removedEdge() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		graph.removeVertex(v1);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse(
				"El m�todo edges no retorna una colecci�n iterable vac�a luego de eliminar el v�rtice que ten�a el �nico arco del grafo.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el m�todo removeEdge lanza InvalidEdgeException con un arco
	 * nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void removeEdge_nullEdge_throwsIEE() throws InvalidEdgeException {
		graph.removeEdge(null);
	}

	/**
	 * Comprueba que el m�todo removeEdge retorna correctamente el elemento del
	 * arco eliminado.
	 */
	@Test
	public void removeEdge_validEdge_returnsElement() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Object element = graph.removeEdge(e);
		assertEquals("El m�todo removeEdge no retorna el elemento del arco eliminado.", o1, element);
	}

	/**
	 * Comprueba que luego de eliminar un arco mediante el m�todo removeEdge, el
	 * mismo ya no es devuelto en la lista de arcos del grafo.
	 */
	@Test
	public void removeEdge_validEdge_removedFromEdgesList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse(
				"El m�todo edges no retorna una colecci�n iterable vac�a luego de eliminar el �nico arco del grafo mediante removeEdge.",
				edgesIterator.hasNext());
	}
	
	/**
	 * Comprueba que luego de eliminar un arco mediante el m�todo removeEdge, el mismo ya no es devuelvo en la lista de arcos incidentes del predecesor.
	 */
	@Test
	public void removeEdge_validEdge_removedFromPredecessorIncidentEdges() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edgesV1 = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIteratorV1 = edgesV1.iterator();
		assertFalse("Luego de eliminar un arco, el mismo sigue apareciendo en la lista de incidentes del v�rtice predecesor.", edgesIteratorV1.hasNext());
	}
	
	/**
	 * Comprueba que luego de eliminar un arco mediante el m�todo removeEdge, el mismo ya no es devuelvo en la lista de arcos incidentes del sucesor.
	 */
	@Test
	public void removeEdge_validEdge_removedFromSuccessorIncidentEdges() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edgesV2 = graph.incidentEdges(v2);
		Iterator<Edge<Object>> edgesIteratorV2 = edgesV2.iterator();
		assertFalse("Luego de eliminar un arco, el mismo sigue apareciendo en la lista de incidentes del v�rtice sucesor.", edgesIteratorV2.hasNext());
	}
	
	/**
	 * Hace una simulaci�n de uso general sobre la estructura.
	 */
	@Test
	public void simulation() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		for (int i = 0; i < 100; i++) {
			graph.insertVertex(new Object());
		}
		Vertex<Object> v3 = graph.insertVertex(new Object());
		Vertex<Object> v4 = graph.insertVertex(new Object());
		
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> iteradorVertices = vertices.iterator();
		int contadorVertices = 0;
		while (iteradorVertices.hasNext()) {
			iteradorVertices.next();
			contadorVertices++;
		}
		assertEquals("Luego de insertar 104 v�rtices, la lista de v�rtices no coincide en tama�o", 104, contadorVertices);
		
		Edge<Object> e1 = graph.insertEdge(v1, v2, new Object());
		Edge<Object> e2 = graph.insertEdge(v1, v3, new Object());
		Edge<Object> e3 = graph.insertEdge(v3, v4, new Object());
		
		assertTrue("areAdjacent retorna false para dos v�rtices adyacentes.", graph.areAdjacent(v1, v2));
		assertTrue("areAdjacent retorna false para dos v�rtices adyacentes.", graph.areAdjacent(v1, v3));
		assertTrue("areAdjacent retorna false para dos v�rtices adyacentes.", graph.areAdjacent(v3, v4));
		assertFalse("areAdjacent retorna true para dos v�rtices no adyacentes.", graph.areAdjacent(v1, v4));
		assertFalse("areAdjacent retorna true para dos v�rtices no adyacentes.", graph.areAdjacent(v2, v3));
		assertFalse("areAdjacent retorna true para dos v�rtices no adyacentes.", graph.areAdjacent(v2, v4));
		
		assertEquals("opposite no retorna el v�rtice opuesto correctamente.", v3, graph.opposite(v1, e2));
		assertEquals("opposite no retorna el v�rtice opuesto correctamente.", v1, graph.opposite(v3, e2));
		assertEquals("opposite no retorna el v�rtice opuesto correctamente.", v1, graph.opposite(v2, e1));
		assertEquals("opposite no retorna el v�rtice opuesto correctamente.", v2, graph.opposite(v1, e1));
		assertEquals("opposite no retorna el v�rtice opuesto correctamente.", v4, graph.opposite(v3, e3));
		assertEquals("opposite no retorna el v�rtice opuesto correctamente.", v3, graph.opposite(v4, e3));
		
		Iterable<Edge<Object>> arcos = graph.edges();
		Iterator<Edge<Object>> iteradorArcos = arcos.iterator();
		int contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("Luego de insertar 3 arcos, la lista de arcos no coincide en tama�o", 3, contadorArcos);
		
		arcos = graph.incidentEdges(v1);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El m�todo incidentEdges no retorna una colecci�n de tama�o 2 para un v�rtice con dos arcos.", 2, contadorArcos);
		
		arcos = graph.incidentEdges(v3);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El m�todo incidentEdges no retorna una colecci�n de tama�o 2 para un v�rtice con dos arcos.", 2, contadorArcos);
		
		graph.removeEdge(e1);
		graph.removeEdge(e2);
		assertFalse("areAdjacent retorna true para dos v�rtices no adyacentes.", graph.areAdjacent(v1, v2));
		assertFalse("areAdjacent retorna true para dos v�rtices no adyacentes.", graph.areAdjacent(v1, v3));
		arcos = graph.edges();
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("Luego de insertar 3 arcos y eliminar 2, la lista de arcos no coincide en tama�o", 1, contadorArcos);
		
		
		arcos = graph.incidentEdges(v3);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El m�todo incidentEdges no retorna una colecci�n de tama�o 1 para un v�rtice con un solo arcos.", 1, contadorArcos);
		
	}

}