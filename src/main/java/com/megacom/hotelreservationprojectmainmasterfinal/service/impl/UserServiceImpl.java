package com.megacom.hotelreservationprojectmainmasterfinal.service.impl;

import com.megacom.hotelreservationprojectmainmasterfinal.dao.UserDao;
import com.megacom.hotelreservationprojectmainmasterfinal.mappers.UserMapper;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.RoomDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.dto.UserDto;
import com.megacom.hotelreservationprojectmainmasterfinal.models.entity.User;
import com.megacom.hotelreservationprojectmainmasterfinal.models.enums.EUserStatus;
import com.megacom.hotelreservationprojectmainmasterfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto save(UserDto usersDto) {
        User user = userMapper.toEntity(usersDto);
        User savedSaved = userDao.save(user);
        return userMapper.toDto(savedSaved);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userDao.findById(id).orElse(null);
        if (user != null) {
            return userMapper.toDto(user);
        } else {
            throw new NoSuchElementException("User does not exist!");
        }
    }

    @Override
    public UserDto update(UserDto usersDto) {
        boolean isExists = userDao.existsById(usersDto.getId());
        if (!isExists) {
            throw new NoSuchElementException("User not found");
        } else {
            User user = userMapper.toEntity(usersDto);
            User savedSaved = userDao.save(user);
            return userMapper.toDto(savedSaved);
        }
    }

    @Override
    public UserDto delete(UserDto usersDto) {
        boolean isExists = userDao.existsById(usersDto.getId());
        if (!isExists) {
            throw new NoSuchElementException("User not found");
        } else {
            User user = userMapper.toEntity(usersDto);
            user.setUserStatus(EUserStatus.DELETED);
            User savedSaved = userDao.save(user);
            return userMapper.toDto(savedSaved);
        }
    }

    @Override
    public UserDto block(UserDto usersDto) {
        boolean isExists = userDao.existsById(usersDto.getId());
        if (!isExists) {
            throw new NoSuchElementException("User not found");
        } else {
            User user = userMapper.toEntity(usersDto);
            user.setUserStatus(EUserStatus.BLOCKED);
            User savedSaved = userDao.save(user);
            return userMapper.toDto(savedSaved);
        }
    }

    @Override
    public UserDto addOwnerAndGiveRights(UserDto userDto) {
        return null;
    }

    @Override
    public List<RoomDto> findByRoomId(Long id) {
        return null;
    }

    @Override
    public List<RoomDto> findByBookingId(Long id) {
        return null;
    }

    @Override
    public List<RoomDto> findByCityId(Long id) {
        return null;
    }

    @Override
    public List<RoomDto> findByHotelId(Long id) {
        return null;
    }

    @Override
    public List<com.megacom.hotelreservationprojectmainmasterfinal.models.dto.UserDto> findByReviewId(Long id) {
        return null;
    }
}
