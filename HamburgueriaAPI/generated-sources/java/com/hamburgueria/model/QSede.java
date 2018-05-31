package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSede is a Querydsl query type for Sede
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSede extends EntityPathBase<Sede> {

    private static final long serialVersionUID = -1000547077L;

    public static final QSede sede = new QSede("sede");

    public final StringPath cidade = createString("cidade");

    public final ListPath<Usuario, QUsuario> clientes = this.<Usuario, QUsuario>createList("clientes", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public final ListPath<Ingrediente, QIngrediente> estoque = this.<Ingrediente, QIngrediente>createList("estoque", Ingrediente.class, QIngrediente.class, PathInits.DIRECT2);

    public final StringPath foto64 = createString("foto64");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Pedido, QPedido> pedidos = this.<Pedido, QPedido>createList("pedidos", Pedido.class, QPedido.class, PathInits.DIRECT2);

    public final ListPath<Produto, QProduto> produtos = this.<Produto, QProduto>createList("produtos", Produto.class, QProduto.class, PathInits.DIRECT2);

    public final ListPath<TipoIngrediente, QTipoIngrediente> tipoIngredientes = this.<TipoIngrediente, QTipoIngrediente>createList("tipoIngredientes", TipoIngrediente.class, QTipoIngrediente.class, PathInits.DIRECT2);

    public QSede(String variable) {
        super(Sede.class, forVariable(variable));
    }

    public QSede(Path<? extends Sede> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSede(PathMetadata<?> metadata) {
        super(Sede.class, metadata);
    }

}

