import React, { Component } from 'react'
import styled from 'styled-components'

const Item = styled.div`
  width: 100%;
  height: 60px;
  display: flex;
  border-bottom: 1px solid #2980b9;
  position: relative;
`
const AvatarPlaceholder = styled.div`
  width: 40px;
  height: 40px;
  border-radius: 100%;
  border: 2px solid #2980b9;
  margin: 0 10px;
  margin-top: 5px;
  background-color: #2980b9;
`
const Username = styled.div`
  margin-top: 10px;
  font-size: 16px;
  font-weight: bold;
`
const LastMessage = styled.div`
  font-size: 12px;
`
const MessageTime = styled.div`
  position: absolute;
  font-size: 11px;
  right: 5px;
  top: 10px;
`

export class UserItem extends Component {
  render() {
    const { user } = this.props;
    return (
      <Item>
        <AvatarPlaceholder></AvatarPlaceholder>
        <div>
          <Username>{user.username}</Username>
          <LastMessage>{user.lastMessage}</LastMessage>
        </div>
        <MessageTime>
          {user.messageTime} min
        </MessageTime>
      </Item>
    )
  }
}

export default UserItem
