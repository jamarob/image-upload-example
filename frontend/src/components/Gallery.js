import styled from 'styled-components/macro'
import Image from './Image'

export function Gallery({ photos, onDelete }) {
  return (
    <Wrapper>
      {photos.map(photo => (
        <Image key={photo.id} {...photo} onDelete={onDelete} />
      ))}
    </Wrapper>
  )
}

const Wrapper = styled.main`
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  overflow-y: scroll;
  padding: 0 24px;
`
