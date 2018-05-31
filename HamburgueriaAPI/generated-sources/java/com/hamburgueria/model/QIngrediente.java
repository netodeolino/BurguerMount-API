package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIngrediente is a Querydsl query type for Ingrediente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIngrediente extends EntityPathBase<Ingrediente> {

    private static final long serialVersionUID = -1865519860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngrediente ingrediente = new QIngrediente("ingrediente");

    public final BooleanPath disponivel = createBoolean("disponivel");

    public final StringPath foto64 = createString("foto64");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final StringPath porcao = createString("porcao");

    public final ListPath<Produto, QProduto> produtos = this.<Produto, QProduto>createList("produtos", Produto.class, QProduto.class, PathInits.DIRECT2);

    public final NumberPath<Integer> qtd = createNumber("qtd", Integer.class);

    public final QSede sede;

    public final QTipoIngrediente tipoIngrediente;

    public final NumberPath<Double> valorBruto = createNumber("valorBruto", Double.class);

    public final NumberPath<Double> valorDeVenda = createNumber("valorDeVenda", Double.class);

    public QIngrediente(String variable) {
        this(Ingrediente.class, forVariable(variable), INITS);
    }

    public QIngrediente(Path<? extends Ingrediente> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIngrediente(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIngrediente(PathMetadata<?> metadata, PathInits inits) {
        this(Ingrediente.class, metadata, inits);
    }

    public QIngrediente(Class<? extends Ingrediente> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
        this.tipoIngrediente = inits.isInitialized("tipoIngrediente") ? new QTipoIngrediente(forProperty("tipoIngrediente"), inits.get("tipoIngrediente")) : null;
    }

}

