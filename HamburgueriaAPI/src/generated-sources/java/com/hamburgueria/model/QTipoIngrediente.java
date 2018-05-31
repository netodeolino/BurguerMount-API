package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTipoIngrediente is a Querydsl query type for TipoIngrediente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTipoIngrediente extends EntityPathBase<TipoIngrediente> {

    private static final long serialVersionUID = -1579446984L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTipoIngrediente tipoIngrediente = new QTipoIngrediente("tipoIngrediente");

    public final StringPath foto64 = createString("foto64");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Ingrediente, QIngrediente> ingredientes = this.<Ingrediente, QIngrediente>createList("ingredientes", Ingrediente.class, QIngrediente.class, PathInits.DIRECT2);

    public final StringPath nome = createString("nome");

    public final QSede sede;

    public QTipoIngrediente(String variable) {
        this(TipoIngrediente.class, forVariable(variable), INITS);
    }

    public QTipoIngrediente(Path<? extends TipoIngrediente> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTipoIngrediente(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTipoIngrediente(PathMetadata<?> metadata, PathInits inits) {
        this(TipoIngrediente.class, metadata, inits);
    }

    public QTipoIngrediente(Class<? extends TipoIngrediente> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

}

