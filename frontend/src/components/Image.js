import styled from 'styled-components/macro'
import axios from 'axios'
import { useQueryClient } from 'react-query'

export default function Image({ url, id }) {
  const queryClient = useQueryClient()

  const deleteImage = () => {
    axios
      .delete('/photo/' + id)
      .then(() => queryClient.invalidateQueries('photos'))
      .catch(error => console.log(error))
  }

  return (
    <Wrapper>
      <img src={url} alt="" />
      <button onClick={deleteImage}>delete</button>
    </Wrapper>
  )
}

const Wrapper = styled.section`
  position: relative;

  img {
    margin: 12px;
    max-width: 300px;
    height: auto;
    border-radius: 8px;
    box-shadow: 1px 2px 4px #999;
  }

  button {
    position: absolute;
    top: 12px;
    right: 12px;
  }
`
