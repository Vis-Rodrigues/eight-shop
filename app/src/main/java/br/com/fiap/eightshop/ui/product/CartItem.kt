package br.com.fiap.eightshop.ui.product

import br.com.fiap.eightshop.data.model.Product

data class CartItem(var product: Product, var quantity: Int = 0)