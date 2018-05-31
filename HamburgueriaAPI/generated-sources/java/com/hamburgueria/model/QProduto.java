package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProduto is a Querydsl query type for Produto
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProduto extends EntityPathBase<Produto> {

    private static final long serialVersionUID = 1789943633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduto produto = new QProduto("produto");

    public final BooleanPath disponivel = createBoolean("disponivel");

    public final StringPath foto64 = createString("foto64");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Ingrediente, QIngrediente> ingredientes = this.<Ingrediente, QIngrediente>createList("ingredientes", Ingrediente.class, QIngrediente.class, PathInits.DIRECT2);

    public final StringPath nome = createString("nome");

    public final QSede sede;

    public final NumberPath<Double> valorBruto = createNumber("valorBruto", Double.class);

    public final NumberPath<Double> valorDeVenda = createNumber("valorDeVenda", Double.class);

    public QProduto(String variable) {
        this(Produto.class, forVariable(variable), INITS);
    }

    public QProduto(Path<? extends Produto> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduto(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduto(PathMetadata<?> metadata, PathInits inits) {
        this(Produto.class, metadata, inits);
    }

    public QProduto(Class<? extends Produto> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

}

