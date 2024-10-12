package lk.ijse.util;

import lk.ijse.dto.impl.customer_dto;
import lk.ijse.dto.impl.item_dto;
import lk.ijse.dto.impl.order_details_dto;
import lk.ijse.dto.impl.order_dto;
import lk.ijse.entity.impl.customer_entity;
import lk.ijse.entity.impl.item_entity;
import lk.ijse.entity.impl.order_entity;
import lk.ijse.entity.impl.orders_details_entity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class mapping {
    @Autowired
    private ModelMapper modelMapper;

    //for customer mapping
    public customer_entity toCustomerEntity(customer_dto customerDto) {
        return modelMapper.map(customerDto,customer_entity.class);
    }

    public customer_dto toCustomerDto(customer_entity customerEntity) {
        return modelMapper.map(customerEntity,customer_dto.class);
    }

    public List<customer_dto> asCustomerDtoList(List<customer_entity> customerEntityList) {
        return modelMapper.map(customerEntityList,new TypeToken<List<customer_dto>>(){}.getType());
    }

    //for item mapping
    public item_dto toItemDto(item_entity itemEntity) {
        return modelMapper.map(itemEntity, item_dto.class);
    }
    public item_entity toItemEntity(item_dto itemDto) {
        return modelMapper.map(itemDto, item_entity.class);
    }
    public List<item_dto> asItemDtoList(List<item_entity> itemEntities) {
        return modelMapper.map(itemEntities, new TypeToken<List<item_dto>>() {}.getType());
    }

    //for orders mapping
    public order_dto toOrdersDto(order_entity orderEntity) {
        return modelMapper.map(orderEntity, order_dto.class);
    }
    public order_entity toOrdersEntity(order_dto orderDto) {
        return modelMapper.map(orderDto, order_entity.class);
    }
    public List<order_dto> asOrdersDtoList(List<order_entity> orderEntities) {
        return modelMapper.map(orderEntities, new TypeToken<List<order_dto>>() {}.getType());
    }

    //for orders details mapping
    public order_details_dto toOrdersDetailsDto(orders_details_entity orderDetailsEntity) {
        return modelMapper.map(orderDetailsEntity, order_details_dto.class);
    }
    public orders_details_entity toOrdersDetailsEntity(order_details_dto orderDetailsDto) {
        return modelMapper.map(orderDetailsDto, orders_details_entity.class);
    }
    public List<order_details_dto> asOrdersDetailsDtoList(List<orders_details_entity> orderDetailsEntities) {
        return modelMapper.map(orderDetailsEntities, new TypeToken<List<order_details_dto>>() {}.getType());
    }
}
