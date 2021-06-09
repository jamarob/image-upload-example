import { AppHeader } from './AppHeader'
import { Gallery } from './Gallery'
import { ImageUpload } from './ImageUpload'
import styled from 'styled-components/macro'
import axios from 'axios'
import { useQuery } from 'react-query'

export default function Page() {
  const { data, isSuccess, isLoading, isError, error } = useQuery(
    'photos',
    () => axios.get('/photo').then(res => res.data)
  )

  return (
    <Wrapper>
      <AppHeader />
      {isError && <p>Something terrible just happened: {error.message}</p>}
      {isLoading && <p>loading</p>}
      {isSuccess && <Gallery photos={data} />}
      <ImageUpload />
    </Wrapper>
  )
}

const Wrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: grid;
  text-align: center;
  grid-template-rows: min-content 1fr;
  padding-bottom: 64px;
`
