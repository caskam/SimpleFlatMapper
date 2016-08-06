package org.simpleflatmapper.map.impl;


import org.simpleflatmapper.map.column.ColumnProperty;
import org.simpleflatmapper.map.mapper.ColumnDefinitionProvider;
import org.simpleflatmapper.map.FieldKey;
import org.simpleflatmapper.map.column.FieldMapperColumnDefinition;
import org.simpleflatmapper.util.BiConsumer;
import org.simpleflatmapper.util.Predicate;

public class IdentityFieldMapperColumnDefinitionProvider<K extends FieldKey<K>> implements ColumnDefinitionProvider<FieldMapperColumnDefinition<K>, K> {
    @Override
    public FieldMapperColumnDefinition<K> getColumnDefinition(K key) {
        return FieldMapperColumnDefinition.<K>identity();
    }

    @Override
    public <CP extends ColumnProperty, BC extends BiConsumer<Predicate<? super K>, CP>> BC forEach(Class<CP> propertyType, BC consumer) {
        return consumer;
    }
}