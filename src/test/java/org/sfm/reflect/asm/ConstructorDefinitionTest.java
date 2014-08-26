package org.sfm.reflect.asm;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.sfm.beans.DbObject;
import org.sfm.beans.DbObject.Type;
import org.sfm.beans.FinalDbObject;

public class ConstructorDefinitionTest {

	@Test
	public void testExtractConstructorsDbObject() throws IOException, NoSuchMethodException, SecurityException {
		List<ConstructorDefinition<DbObject>> dbObjectConstructors = ConstructorDefinition.extractConstructors(DbObject.class);
		assertEquals(1, dbObjectConstructors.size());
		assertEquals(0, dbObjectConstructors.get(0).getParameters().length);
		assertEquals(DbObject.class.getConstructor(), dbObjectConstructors.get(0).getConstructor());
		
	}
	
	@Test
	public void testExtractConstructorsFinalDbObject() throws IOException, NoSuchMethodException, SecurityException {

		List<ConstructorDefinition<FinalDbObject>> finalDbObjectConstructors = ConstructorDefinition.extractConstructors(FinalDbObject.class);
		assertEquals(1, finalDbObjectConstructors.size());
		assertEquals(6, finalDbObjectConstructors.get(0).getParameters().length);
		
		assertEquals(long.class, finalDbObjectConstructors.get(0).getParameters()[0].getType());
		assertEquals(String.class, finalDbObjectConstructors.get(0).getParameters()[1].getType());
		assertEquals(String.class, finalDbObjectConstructors.get(0).getParameters()[2].getType());
		assertEquals(Date.class, finalDbObjectConstructors.get(0).getParameters()[3].getType());
		assertEquals(Type.class, finalDbObjectConstructors.get(0).getParameters()[4].getType());
		assertEquals(Type.class, finalDbObjectConstructors.get(0).getParameters()[5].getType());

		assertEquals("id", finalDbObjectConstructors.get(0).getParameters()[0].getName());
		assertEquals("name", finalDbObjectConstructors.get(0).getParameters()[1].getName());
		assertEquals("email", finalDbObjectConstructors.get(0).getParameters()[2].getName());
		assertEquals("creationTime", finalDbObjectConstructors.get(0).getParameters()[3].getName());
		assertEquals("typeOrdinal", finalDbObjectConstructors.get(0).getParameters()[4].getName());
		assertEquals("typeName", finalDbObjectConstructors.get(0).getParameters()[5].getName());

		
		assertEquals(FinalDbObject.class.getConstructor(long.class, String.class, String.class, Date.class, Type.class, Type.class), finalDbObjectConstructors.get(0).getConstructor());

	}

}