import React, { Component } from 'react'
import styled from 'styled-components'
import { UserItem } from '../UserItem/UserItem'
import { SearchBar } from '../SearchBar/SearchBar'

const List = styled.div`
  width: 35%;
  height: 100%;
  border-right: 2px solid #2980b9;
  display: flex;
  flex-direction: column;
  padding: 5px 0;
`
const usersMock = [
  {
    id: 1,
    username: 'John Doe',
    lastMessage: 'Defined but never used...',
    messageTime: 20,
  },
  {
    id: 2,
    username: 'Roger Black',
    lastMessage: 'Hello ut never used...',
    messageTime: 18,
  },
  {
    id: 3,
    username: 'Jack White',
    lastMessage: 'Hello World!',
    messageTime: 44,
  },
  {
    id: 4,
    username: 'Johnatan Leandoer',
    lastMessage: 'Defined but never used...',
    messageTime: 69,
  },
  {
    id: 5,
    username: 'Jackie Brown',
    lastMessage: 'Astroworld but nev...',
    messageTime: 9,
  },
  {
    id: 6,
    username: 'George Small',
    lastMessage: 'Get the bag...',
    messageTime: 3,
  },
  {
    id: 7,
    username: 'Dale Cooper',
    lastMessage: 'How high?',
    messageTime: 29,
  },
  {
    id: 8,
    username: 'David Lynch',
    lastMessage: '...',
    messageTime: 5,
  }
]

export class UserList extends Component {
  render() {
    return (
      <List>
        {usersMock.map(user => (
          <UserItem key={user.id} user={user}></UserItem>
        ))}
        <SearchBar></SearchBar>

      </List>
    )
  }
}

export default UserList
